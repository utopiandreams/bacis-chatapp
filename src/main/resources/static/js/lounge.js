document.getElementById('create-chatroom').addEventListener('click', function() {
    window.location.href = '/chat/creation';
});

fetchChatrooms();





function fetchChatrooms() {
    fetch('/api/chatroom')
        .then(response => {
            if (!response.ok) throw new Error("Network response was not ok");
            return response.json();})
        .then(data => {
            console.log(data);
            data.sort((a, b) => {
                let dateA = new Date(a.lastChatAt);
                let dateB = new Date(b.lastChatAt);
                return dateB - dateA;
            });
            data.forEach(roomInfo => {
                let chatroom = createChatroom(roomInfo);
                document.getElementById('chatroom-frame').appendChild(chatroom);
            })
        })
        .then(() => {
                Array.from(document.getElementsByClassName('chatroom')).forEach(chatroom => {
                    console.log("뭔데?")
                    chatroom.addEventListener('click', function() {
                        let chatroomId = this.dataset.chatroomId;
                        console.log(chatroomId)
                        window.location.href = `/chat/${chatroomId}`
                        ;})
                    ;}
                );})
        .catch(error => {
            console.error("There was a problem with the fetch operation:", error.message);
        });
}

function createChatroom(roomInfo) {
    // 새로운 .chatroom 요소 생성
    let chatroom = document.createElement('div');
    chatroom.classList.add('chatroom');
    chatroom.setAttribute('data-chatroom-id', roomInfo.id);

    // 제목 요소 생성 및 내용 설정
    let titleElem = document.createElement('div');
    titleElem.classList.add('title');
    titleElem.textContent = roomInfo.title;

    // 대화일 요소 생성 및 내용 설정
    let dateElem = document.createElement('div');
    dateElem.classList.add('date');
    dateElem.textContent = timeAgo(roomInfo.lastChatAt);

    // 아이템 수 요소 생성 및 내용 설정
    let userElem = document.createElement('div');
    userElem.classList.add('participates');
    fetch(`/api/chatroom/${roomInfo.id}/sub`)
        .then(response => response.text())
        .then(data =>
            userElem.textContent=`(${data}/${roomInfo.limit})`)


    // 위에서 생성한 요소들을 .chatroom 요소에 추가
    chatroom.appendChild(titleElem);
    chatroom.appendChild(dateElem);
    chatroom.appendChild(userElem);

    return chatroom;
}

function currentUsers(roomId) {
    fetch(`/api/chatroom/${roomId}/sub`)
        .then(data => data.text())
        .then(howmany => {
            return howmany;
        })

}


function timeAgo(localDateTimeText) {
    // LocalDateTime 텍스트를 moment 객체로 변환합니다.
    const dateTime = moment(localDateTimeText, "YYYY-MM-DDTHH:mm:ss");

    // 현재 시간과의 차이를 분 단위로 계산합니다.
    const diffMinutes = moment().diff(dateTime, 'minutes');

    if (diffMinutes < 60) { // 1시간 이내의 경우
        return `${diffMinutes}분 전`;
    } else if (diffMinutes < 1440) { // 24시간 (하루) 이내의 경우
        const hours = Math.floor(diffMinutes / 60);
        const minutes = diffMinutes % 60;
        return `${hours}시간 ${minutes}분 전`;
    } else { // 하루가 지난 경우
        const days = Math.floor(diffMinutes / 1440);
        return `${days}일 전`;
    }
}