package meetme.reftable

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReferenceTableServiceImpl : ReferenceTableService {

    @Autowired
    lateinit var referenceTableRepository: ReferenceTableRepository

    override fun saveReferenceTable(referenceTable: ReferenceTable) = referenceTableRepository.save(referenceTable)
    override fun findById(refTableId: String) = referenceTableRepository.findByRefTableIdOrRefTableId(refTableId, "lastOnline")
    override fun findAll() = referenceTableRepository.findAll().groupBy { it.refTableId }.toList()
    override fun saveReferenceTable(referenceTable: List<ReferenceTable>) =
            referenceTableRepository.saveAll(referenceTable) as List<ReferenceTable>

}