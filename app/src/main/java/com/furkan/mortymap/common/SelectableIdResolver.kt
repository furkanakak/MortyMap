package com.furkan.mortymap.common

import java.lang.reflect.Field
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

private sealed class CachedField {
    data class Present(val field: Field) : CachedField()
    data object Absent : CachedField()
}

private object AnnotatedFieldCache {
    val map = ConcurrentHashMap<Class<*>, CachedField>()
}

fun <A : Annotation> Any.getField(annotation: KClass<A>): Any? {
    val cls = this.javaClass

    val marker = AnnotatedFieldCache.map[cls] ?: run {
        val found = findAnnotatedField(cls, annotation)
        val cached = if (found != null) CachedField.Present(found) else CachedField.Absent
        AnnotatedFieldCache.map.putIfAbsent(cls, cached)
        cached
    }

    return when (marker) {
        is CachedField.Present -> marker.field.get(this)
        CachedField.Absent -> null
    }
}

inline fun <reified A : Annotation> Any.getField(): Any? =
    getField(A::class)

private fun <A : Annotation> findAnnotatedField(
    start: Class<*>,
    annotation: KClass<A>
): Field? {
    var c: Class<*>? = start
    while (c != null && c != Any::class.java) {
        for (f in c.declaredFields) {
            if (f.isAnnotationPresent(annotation.java)) {
                f.isAccessible = true
                return f
            }
        }
        c = c.superclass
    }
    return null
}