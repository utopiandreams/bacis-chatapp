const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/chat/ws-endpoint'
});

let userId = null;
init();
stompClient.activate();
stompClient.onConnect = (frame) => {
    stompClient.subscribe('/topic/chatroom', (stomp) => {
        let message = JSON.parse(stomp.body);
        appendMessage(message);
        scrollDown();
    });
};

function sendMessage() {
    console.log("전송");
    stompClient.publish({
        destination: "/chat/room",
        body: JSON.stringify(
            {'content': $("#chat-outgoing-msg").val()}
        )
    });
}

$(function () {
    let input = $( "#chat-outgoing-msg" );
    $( "#chat-send" ).click(() => {
        if(input.val().trim() != "") {
            sendMessage()
            input.val("");
        }
    });
    $( "#chat-outgoing-msg" ).keydown(function (event){
        if(event.which == 13 ){
            if(input.val().trim() != "") {
                sendMessage()
                input.val("");
            }
        }
    });
});

async function init() {
    try {
        fetch('http://localhost:8080/me', {
            method: 'GET'})
            .then(response => response.json())
            .then(data => {userId = data})
    } catch (error) {
        console.error('Error:', error);
    }

    console.log("??")

    try {
        fetch('http://localhost:8080/chat/recent', {
            method: 'GET'})
            .then(response => response.json())
            .then(messages => {
                messages.forEach(message => {
                    appendMessage(message);
                })
            })
    } catch (error) {
        console.error('Error:', error);
    }
    scrollDown();
}

function scrollDown() {
    const box = document.getElementById('chat-frame');
    box.scrollTop = box.scrollHeight;
}

function appendMessage(message) {
    if (message.senderId != userId) {
        addIncomingMessage(message.senderName, message.content, formatDate(message.sentAt));
    } else {
        addOutgoingMessage(message.senderName, message.content, formatDate(message.sentAt));
    }
}

function formatDate(inputDateStr) {
    const inputDate = new Date(inputDateStr);
    const currentDate = new Date();

    // 시간 및 분을 두 자릿수로 만들기
    let hours = inputDate.getHours();
    let minutes = inputDate.getMinutes();
    hours = hours < 10 ? '0' + hours : hours;
    minutes = minutes < 10 ? '0' + minutes : minutes;

    // 입력 날짜와 현재 날짜 차이 계산
    const oneDay = 24 * 60 * 60 * 1000; // 하루의 밀리초 수
    const diffDays = Math.round((currentDate - inputDate) / oneDay);

    if (diffDays === 0) {
        return `${hours}시 ${minutes}분`;
    } else if (diffDays === 1) {
        return `${hours}시 ${minutes}분 - 어제`;
    } else if (diffDays === 2) {
        return `${hours}시 ${minutes}분 - 그저께`;
    } else {
        const month = inputDate.getMonth() + 1; // 월은 0부터 시작하므로 1을 더함
        const day = inputDate.getDate();
        return `${hours}시 ${minutes}분 - ${month}/${day}`;
    }
}

function addIncomingMessage(sender, message, time) {
    const chatContainer = document.getElementById('chat-box');
    const msgDiv = document.createElement('div');
    msgDiv.className = "incoming_msg";

    const senderName = document.createElement('p');
    senderName.className = "sender_name";
    senderName.textContent = sender;

    const receivedDiv = document.createElement('div');
    receivedDiv.className = "received_msg";

    const withMsgDiv = document.createElement('div');
    withMsgDiv.className = "received_with_msg";

    const messageText = document.createElement('p');
    messageText.textContent = message;

    const timeDateSpan = document.createElement('span');
    timeDateSpan.className = "time_date";
    timeDateSpan.textContent = time;

    withMsgDiv.appendChild(messageText);
    withMsgDiv.appendChild(timeDateSpan);
    receivedDiv.appendChild(withMsgDiv);
    msgDiv.appendChild(senderName);
    msgDiv.appendChild(receivedDiv);
    chatContainer.appendChild(msgDiv);
}

function addOutgoingMessage(sender, message, time) {
    const chatContainer = document.getElementById('chat-box');
    const msgDiv = document.createElement('div');
    msgDiv.className = "outgoing_msg";

    const senderDiv = document.createElement('div');
    senderDiv.className = "sender_me";
    senderDiv.textContent = "나(" + sender + ")";

    const sentDiv = document.createElement('div');
    sentDiv.className = "sent_msg";

    const withMsgDiv = document.createElement('div');
    withMsgDiv.className = "received_with_msg";

    const messageText = document.createElement('p');
    messageText.textContent = message;

    const timeDateSpan = document.createElement('span');
    timeDateSpan.className = "time_date";
    timeDateSpan.textContent = time;

    withMsgDiv.appendChild(messageText);
    withMsgDiv.appendChild(timeDateSpan);
    sentDiv.appendChild(withMsgDiv);
    msgDiv.appendChild(senderDiv);
    msgDiv.appendChild(sentDiv);

    chatContainer.appendChild(msgDiv);
}