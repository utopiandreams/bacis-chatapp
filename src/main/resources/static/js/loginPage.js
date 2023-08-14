console.log("js 연결")

document.getElementById('join').addEventListener('click', async function () {
    // 입력된 username과 password를 가져옵니다.
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const params = new URLSearchParams();
    params.append('username', username);
    params.append('password', password);

    try {
        // /join 엔드포인트로 POST 요청을 보냅니다.
        const response = await fetch('http://localhost:8080/join', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params.toString()
        });

        // 응답을 처리합니다.
        const data = await response.text();

        let result = document.getElementById('msg');
        result.innerHTML = data


    } catch (error) {
        // 에러 발생 시 처리 로직
        console.error('Error:', error);
    }

})
