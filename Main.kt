import kotlin.system.exitProcess

fun menu ():String{
    val menu = """
       | Asking For Fun: a Quiz Game! |
     a) Single Player
     b) Multiplayer
     c) Exit 
    """.trimIndent();

    return menu;
}
fun handleOption(optionSelected:String){
    when (optionSelected) {
        "a" -> singlePlayer();
        "b" -> multiplayer();
        "c" -> {
            println("End Game ");
            exitProcess(0);
        }
        else -> {
            println("Invalid Value. Please, try again");
            print("Option: ");
            val op2 = readLine()!!;
            return handleOption(op2);
        }
    }
}
fun getCategories(): String {
    val menuCategories ="""
        1 - Brazilian Rap
        2 - Soccer
    """.trimIndent()

    println(menuCategories);
    print("Option: ");
    val option = readLine()!!;
    return option;
}
fun getQuestions(categorySelected: String):Map<String, String>{
    val rap = mapOf(
        "What year was the 'lyrical year'?,b" to "a) 2016\nb) 2017\nc) 2018",
        "What is the name of the song number 7 of the album 'Castelos e Ruínas' ?,c" to "a) Sigo na sombra\nb) Um dia de chuva qualquer\nc) Castelos e ruínas",
        "What year was the album 'O Menino que queria ser Deus' released?,c" to "a) 2016\nb) 2017\nc) 2018"
    );
    val soccer = mapOf(
        "In what year did Brazil win its first World Cup?,a" to "a) 1958\nb) 1954\nc)1962?\n",
        "Which player has the most goals in history?,c" to "a) Pelé\nb) Romário\nc)Cristiano Ronaldo?\n",
        "Who is the Brazilian champion of 1987?,b" to "a) Flamengo\nb) Sport\nc) São Caetano?\n"
    );
    if(categorySelected == "1") return rap;
    else return soccer;

}
fun askQuestion(question:String, alternatives:String):Int{
    val qEa = question.split(",")
    val q = qEa[0];
    val answer =qEa[1];

    println("\n${q}");
    println(alternatives);
    print("Option: ")
    val option = readLine()!!;

    if(option == answer) return 10;
    else return 0;
}
fun singlePlayer(){
    var points = 0;

    print("\nLet's Play! First, enter your name: ");
    val name = readLine()!!;
    println("$name, choose a category:")
    val category = getCategories();

    val questions = getQuestions(category);
    for(q in questions.entries){
        points += askQuestion(q.key, q.value);
    };

    println("Ok $name,  You made $points points!");
}
fun play(start: String, second: String, questions: Map<String, String>){
    var startPoints = 0;
    var secondPoints = 0;
    var i = 1;

    println("\nThe player that starts is chosen randomly. This time $start will start.");

    for(q in questions.entries){
        if((i%2) != 0){
            startPoints += askQuestion(q.key, q.value);
        }else{
            secondPoints += askQuestion(q.key, q.value);
        }
    };

    println("\n$start: $startPoints points");
    println("$second: $secondPoints points");

}
fun multiplayer(){
    println("Enter the players name:")
    print("Player 1 --> ");
    val player1 = readLine()!!;
    print("Player 2 --> ");
    val player2 = readLine()!!;

    println("\nChoose a category:")
    val category = getCategories();
    val questions = getQuestions(category);


    val bingo = (1..2).random();
    if(bingo == 1){
        play(player1, player2, questions);
    }else{
        play(player2, player1, questions);
    }
}

fun main() {
    println(menu());
    print("Option: ");
    val optionSelected = readLine()!!;
    handleOption(optionSelected)

}

