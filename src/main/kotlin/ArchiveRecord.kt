data class ArchiveRecord( override var name: String) : RecordInterface {
    var lstNotes : MutableList<NoteRecord> = mutableListOf()
}