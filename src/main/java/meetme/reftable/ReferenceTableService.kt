package meetme.reftable


interface ReferenceTableService {

    fun saveReferenceTable(referenceTable: ReferenceTable): ReferenceTable
    fun findAll(): List<Pair<String, List<ReferenceTable>>>
    fun findById(refTableId: String): List<ReferenceTable>
    abstract fun saveReferenceTable(referenceTable: List<ReferenceTable>): List<ReferenceTable>
}