var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/prescription/queue', function (greeting) {
            console.log("greeting;" + greeting);
            showGreeting(greeting.body);
        });
    });
}

function load() {
    $.getJSON("/all", function (data) {
        $.each(data, function (key, val) {
            $("#greetings").append("<tr><td>" + val.id + "</td></tr>");
        })
    })
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/add/kk", {}, JSON.stringify({'name': $("#name").val()}));
}

function generateCode() {
    $.ajax({
        url : '/code/generate',
        type : 'GET',
        success : function(response) {
            console.log("generated Id: " + response);
            redirectToGeneratedId(response);
        },
        error : function(request, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}

function redirectToGeneratedId(generatedId) {
    window.location.href = "/web/code/" + generatedId;
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#load").click(function () {
        load();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});