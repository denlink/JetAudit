package utils

import tanvd.audit.model.external.records.InformationObject
import tanvd.audit.model.external.records.ObjectState
import tanvd.audit.model.external.types.objects.ObjectType
import tanvd.audit.model.internal.AuditRecordInternal
import java.math.BigInteger
import java.security.SecureRandom
import java.util.*


internal object SamplesGenerator {

    private val random = SecureRandom()

    fun getRecordInternal(vararg obj: Any, information: MutableSet<InformationObject<*>>): AuditRecordInternal {
        val listObjects = ArrayList<Pair<ObjectType<*>, ObjectState>>()
        for (o in obj) {
            val type = ObjectType.resolveType(o::class)
            listObjects.add(type to type.serialize(o))
        }
        return AuditRecordInternal(listObjects, information)
    }


    fun getRandomInt(bound: Int = Int.MAX_VALUE): Int {
        return random.nextInt(bound)
    }

    fun getRandomString(length: Int = 32): String {
        return BigInteger(130, random).toString(length)
    }


    //01/01/2000
    fun getMillenniumStart(): Date {
        //start of year 2000
        return getDate("01/01/2000")
    }
}