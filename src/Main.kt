fun generateSecretNumber(): String {
    // Список чисел
    val numbers = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')
    // Перемешанный список
    val shuffledNumbers = numbers.shuffled()
    // берём первые 4 элемента
    val secret = shuffledNumbers.take(4)
    // Возвращаем строку
    return secret.joinToString("")
}

fun inputCheck(input: String): Boolean{
    if (input.length != 4){
        return false
    }

    if (!input.all { it.isDigit() }){
        return false
    }

    val uniqueChars = input.toCharArray().toSet()
    if (uniqueChars.size != 4){
        return false
    }
    else return true
}

fun secretCheck(input: String, secret: String): Boolean{
    var cow = 0
    var bull = 0
    for ((index, num) in input.withIndex()) {
        val SecretIndex = secret.indexOf(num)
        if (SecretIndex != -1){
            if(SecretIndex == index){
                bull++
            }
            else{
                cow++
            }
        }
    }
    if(cow != 0 || bull != 0 && bull != 4) {
        println("$cow коровы и $bull быков.")
        return false
    }
    else if(bull == 4){
        println("Вы отгадали секретное число!")
        return true
    }
    else{
        println("Коров и быков нет!")
        return false
    }
}

fun main() {
    val secretNumber = generateSecretNumber()

    print("Введите строку из 4 уникальных цифр: ")
    var input = readLine() ?: ""
    while (!inputCheck(input)) {
        print("Некорректный ввод! Пожалуйста, введите строку из 4 уникальных цифр:")
        input = readLine() ?: ""
    }

    while (!secretCheck(input, secretNumber)) {
        print("Введите строку из 4 уникальных цифр: ")
        input = readLine() ?: ""
        while (!inputCheck(input)) {
            print("Некорректный ввод! Пожалуйста, введите строку из 4 уникальных цифр:")
            input = readLine() ?: ""
        }
    }
}