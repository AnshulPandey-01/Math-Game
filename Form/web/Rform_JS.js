function func() {
    var f = document.getElementById('message');
    var a = document.getElementById("pass").value;
    var b = document.getElementById("Cpass").value;
    if (a !== b) {
        f.innerHTML = "Password didn't match";
        return false;
    }

    var c = document.getElementById("opt").value;
    if (c === "null") {
        document.getElementById("messagec").innerHTML = "Please select city";
        return false;
    }
}

function effect() {
    anime({
        targets: 'div.box',
        translateY: [
            {value: 800, duration: 3000}],

        loop: false
    });
}
function effects() {
    anime({
        targets: 'div.login',
        translateY: [
            {value: 400, duration: 2000}],

        loop: false
    });
}
var a = document.getElementById("a");
var b = document.getElementById("b");
var c = document.getElementById("l");
var d = document.getElementById("k");
function myg() {
    var morphing = anime({
        targets: '.polymorph',
        points: [
            {value: '0 0, 125 0, 125 100, 0 100'},
            {value: '0 0, 0 0, 125 100, 0 100'},
            {value: '0 0, 0 0, 36 100, 0 100'}
        ],
        easing: 'easeOutQuad',
        duration: 2000,
        loop: false
    });

    a.style.display = "none";
    b.style.display = "none";
    d.style.display = "none";
    setTimeout(effect, 300);
}

function my() {
    var morphing = anime({
        targets: '.polymorph',
        points: [
            {value: '0 0, 125 0, 125 100, 0 100'},
            {value: '0 0, 0 0, 125 100, 0 100'},
            {value: '0 0, 0 0, 36 100, 0 100'}
        ],
        easing: 'easeOutQuad',
        duration: 2000,
        loop: false
    });

    a.style.display = "none";
    b.style.display = "none";
    c.style.display = "none";
    setTimeout(effects, 400);
}