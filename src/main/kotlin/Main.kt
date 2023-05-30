import java.util.*

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    println("Добро пожаловать в заметки")

    var lstArchives: MutableList<ArchiveRecord> = mutableListOf()
    while (true) {
        var archMenuIndex =
            showMenuAndRequestChoosedMenuIndex(lstArchives, "Список архивов:", "архив")
        when (archMenuIndex) {
            (lstArchives.size + 1) -> {
                // выход из приложения
                println("\nПриложение завершено")
                break
            }
            0 -> {
                // создание архива
                var newArchName =
                    requestToUserAboutNewName(lstArchives, "Введите название создаваемго архива:")
                lstArchives.add(ArchiveRecord(newArchName))
            }
            in 1..lstArchives.size -> {
                // переход в меню заметок данного архива
                var archRec = lstArchives[archMenuIndex - 1];
                while (true) {
                    var noteMenuIndex =
                        showMenuAndRequestChoosedMenuIndex(
                            archRec.lstNotes,
                            "Список заметок:",
                            "заметку"
                        );

                    when (noteMenuIndex) {
                        (archRec.lstNotes.size + 1) -> {
                            println("Переход в предыдущее меню")
                            break
                        }
                        0 -> {
                            // создание имени заметки
                            var newNoteName = requestToUserAboutNewName(
                                archRec.lstNotes,
                                "Введите название создаваемой заметки:"
                            )
                            var newNote = NoteRecord(newNoteName);
                            // заполнение содержимого заметки
                            println("\nВведите содержимое заметки \"${newNote.name}\":")
                            newNote.content = input.nextLine()
                            archRec.lstNotes.add(newNote);
                            // запись данных новой заметки в архив
                            lstArchives.removeAt(archMenuIndex - 1);
                            lstArchives.add(archMenuIndex - 1, archRec);
                        }
                        in 1..archRec.lstNotes.size -> {
                            // выбор заметки для просмотра
                            var newNote = archRec.lstNotes[noteMenuIndex - 1];
                            println("\nВывод содержимого заметки \"${newNote.name}\":")
                            println("${newNote.content}")
                        }
                    }
                }
            }
        }
    }
}