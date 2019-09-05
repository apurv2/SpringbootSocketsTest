package meetme.reftable

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("ReferenceTable")
data class ReferenceTable(
        @Id
        var id: Int?,
        @Indexed
        var refTableId: String,
        var code: String?,
        var description: String?)