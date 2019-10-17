package meetme.reftable

import meetme.chats.ChatThread
import org.springframework.data.repository.CrudRepository

interface ReferenceTableRepository : CrudRepository<ReferenceTable, String> {
    fun findByRefTableIdOrRefTableId(refTableId : String, refTableId2 : String) : List<ReferenceTable>
}


