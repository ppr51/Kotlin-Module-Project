data class ArchiveRecord( override var name: String) : RecordInterface {
    var listNotes : MutableList<NoteRecord> = mutableListOf()
}