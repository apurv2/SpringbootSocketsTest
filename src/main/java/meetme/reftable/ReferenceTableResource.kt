package meetme.reftable

import meetme.users.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class ReferenceTableResource {

    @Autowired
    private lateinit var referenceTableService: ReferenceTableService

    @PostMapping("/referenceTables")
    @CrossOrigin
    fun createReferenceTable(@RequestBody referenceTables: List<ReferenceTable>) =
            referenceTableService.saveReferenceTable(referenceTables)

    @GetMapping("/referenceTables/{refTableId}")
    @CrossOrigin
    fun findById(@PathVariable("refTableId") refTableId: String) = referenceTableService.findById(refTableId)

    @GetMapping("/referenceTables")
    @CrossOrigin
    fun findAll() = referenceTableService.findAll()

}

