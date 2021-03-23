var numOne;
var numTwo;
var operation;
var correctAnswer;
var counter = 0;
var best = Number(document.getElementById("highScore").innerHTML);

var inputField = document.getElementById("answer");
inputField.addEventListener("keypress", function(event) {
    if ((event.code==="Enter" || event.code==="NumpadEnter") && !(isNaN(inputField.value) || inputField.value==="")) {
        event.preventDefault();
        addition();
        setTimeout(randomExpression, 500);
    }
});

function addition(){
    var userAnswer = inputField.value;
    var displayField = document.getElementById("output");
    var streakField = document.getElementById("streak");
    var bestStreak = document.getElementById("Bstreak");
    if(isNaN(userAnswer) || userAnswer===""){
        displayField.innerHTML = userAnswer + " is not a number!";
    }else{
        if(userAnswer==correctAnswer){
            displayField.style.color = "#05bb60";
            displayField.innerHTML = "Correct! " + correctAnswer;
            counter++;
            streakField.innerHTML = "Current Streak: " + counter;
            if(counter>best){
                best = counter;
                fetch('http://localhost:8080/Form/HighScore', {method: 'POST', body: new URLSearchParams({"highScore": best})})
                .then(results => results.json()).then(console.log("Hi"));
                bestStreak.innerHTML = "Best Streak: " + best;
            }
        }else{
            displayField.style.color = "#ff0000";
            displayField.innerHTML = "Incorrect! " + userAnswer + "<br>" + "Correct Answer: " + correctAnswer;
            streakField.innerHTML = "Current Streak: " + counter;
            counter = 0;
        }
    }
}

function randomExpression(){
    inputField.value = "";
    numOne = Math.floor(Math.random()*10);
    numTwo = Math.floor(Math.random()*9) + 1;
    operation = Math.floor(Math.random()*4) + 1;
    if(operation===1){
        document.getElementById("expression").innerHTML = numOne + " + " + numTwo;
        correctAnswer = numOne + numTwo;
    }if(operation===2){
        document.getElementById("expression").innerHTML = numOne + " - " + numTwo;
        correctAnswer = numOne - numTwo;
    }if(operation===3){
        document.getElementById("expression").innerHTML = numOne + " * " + numTwo;
        correctAnswer = numOne * numTwo;
    }if(operation===4){
        document.getElementById("expression").innerHTML = numOne + " / " + numTwo;
        correctAnswer = Math.floor(numOne / numTwo);
    }
}

randomExpression();