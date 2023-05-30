import java.util.*

fun <T : RecordInterface> showMenu(lst: MutableList<T>, title: String, makeWhat: String) {
    println("\n $title")
    println("0. Создать $makeWhat")
    for (i in 0..lst.size - 1) {
        println("${i + 1}. ${lst[i].name}")
    }
    println("${lst.size + 1}. Выход")

    println("\nДля продолжения введите номер необходимого пункта меню:")
}

fun <T : RecordInterface> showMenuAndRequestChoosedMenuIndex(
    lst: MutableList<T>,
    title: String,
    makeWhat: String
): Int {
    val input = Scanner(System.`in`)
    var menuIndex: UInt? = null
    do {
        showMenu(lst, title, makeWhat);

        // затем проверить на число
        menuIndex = input.nextLine().toUIntOrNull()
        if (null == menuIndex) {
            println("Ошибка! При выборе пункта меню необходимо вводить положительную цифру. Попробуйте ещё раз")
        } else if (menuIndex > ((lst.size + 1).toUInt())) {
            println("Ошибка! Такой цифры нет в пунктах меню. Попробуйте ещё раз")
        }
    } while (null == menuIndex)
    return menuIndex.toInt()
}

fun <T : RecordInterface> requestToUserAboutNewName(
    lst: MutableList<T>,
    requestStr: String
): String {
    val input = Scanner(System.`in`)
    var newNameStr = ""
    while (true) {
        println("\n$requestStr")
        newNameStr = input.nextLine()
        if (!newNameStr.isEmpty()) {
            var isExist = false;
            for (rec: T in lst) {
                if (rec.name == newNameStr) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                println("Ошибка, Вы ввели существующее название! Попробуйте ещё раз")
            } else {
                break; // название прошло проверки на корректность
            }
        } else {
            println("Ошибка, Вы ввели пустое название! Попробуйте ещё раз")
        }
        //очищаем перед новой попыткой
        newNameStr = "";
    }
    return newNameStr;
}

