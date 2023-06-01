import java.util.*

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    println("Добро пожаловать в заметки")

    var listArchives: MutableList<ArchiveRecord> = mutableListOf()
    while (true) {
        var archMenuIndex =
            showMenuAndRequestChoosedMenuIndex(listArchives, "Список архивов:", "архив")
        when (archMenuIndex) {
            (listArchives.size + 1) -> {
                // выход из приложения
                println("\nПриложение завершено")
                break
            }
            0 -> {
                // создание архива
                var newArchName =
                    requestToUserAboutNewName(listArchives, "Введите название создаваемго архива:")
                listArchives.add(ArchiveRecord(newArchName))
            }
            in 1..listArchives.size -> {
                // переход в меню заметок данного архива
                var archRec = listArchives[archMenuIndex - 1]
                while (true) {
                    var noteMenuIndex =
                        showMenuAndRequestChoosedMenuIndex(
                            archRec.listNotes,
                            "Список заметок:",
                            "заметку"
                        );

                    when (noteMenuIndex) {
                        (archRec.listNotes.size + 1) -> {
                            println("Переход в предыдущее меню")
                            break
                        }
                        0 -> {
                            // создание имени заметки
                            var newNoteName = requestToUserAboutNewName(
                                archRec.listNotes,
                                "Введите название создаваемой заметки:"
                            )
                            var newNote = NoteRecord(newNoteName)
                            // заполнение содержимого заметки

                            while (true) {
                                println("\nВведите содержимое заметки \"${newNote.name}\":")
                                newNote.content = input.nextLine()
                                if (!newNote.content.isEmpty()) {
                                    break
                                }
                                else
                                {
                                    println("Ошибка, Вы не заполнили ничем содержимое заметки! Попробуйте ещё раз")
                                }
                            }
                            archRec.listNotes.add(newNote)
                            // запись данных новой заметки в архив
                            listArchives.removeAt(archMenuIndex - 1)
                            listArchives.add(archMenuIndex - 1, archRec)
                        }
                        in 1..archRec.listNotes.size -> {
                            // выбор заметки для просмотра
                            var newNote = archRec.listNotes[noteMenuIndex - 1]
                            println("\nВывод содержимого заметки \"${newNote.name}\":")
                            println("${newNote.content}")
                        }
                    }
                }
            }
        }
    }
}