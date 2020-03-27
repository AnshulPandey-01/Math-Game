var numOne;
var numTwo;
var correctAnswer;

function addition(){
    var userAnswer = document.getElementById("answer").value;
    if(isNaN(userAnswer)){
        document.getElementById("output").innerHTML = userAnswer + "is not a number!";
    }else{
        if(userAnswer == correctAnswer){
            document.getElementById("output").innerHTML = "Correct! " + numOne + " + " + numTwo + " = " + correctAnswer;
        }else{
            document.getElementById("output").innerHTML = "Incorrect! " + numOne + " + " + numTwo + " = " + correctAnswer + ", not " + userAnswer;
        }
    }
}

function randomExpression(){
    numOne = Math.floor(Math.random()*10) + 1;
    numTwo = Math.floor(Math.random()*10) + 1;
    document.getElementById("expression").innerHTML = numOne + " + " + numTwo;
    correctAnswer = numOne + numTwo;
}