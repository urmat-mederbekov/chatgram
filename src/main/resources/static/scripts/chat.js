const baseUrl = 'http://localhost:8080';
const restUrl = 'http://localhost:8080/api';
const csrfToken = document.querySelector('[name=_csrf_token]').content;

async function getMessages() {
    const response = await fetch(restUrl + '/messages');
    if (response.ok) {
        return await response.json();
    } else {
        alert("Error" + response.error());
    }
}
async function loadMessages(){
    const messages = await getMessages();
    messages.reverse();
    let last_rest_message_id = messages[messages.length - 1].id;
    let last_chat_message_id = document.getElementsByClassName("chat")[0].lastChild.id;
    messages.forEach(message => {

        if (message === messages[messages.length - 1] && last_chat_message_id != last_rest_message_id){

            document.getElementsByClassName('chat')[0].firstChild.remove();
        }

        !document.getElementById(message.id)&&
        addMessageElement(createMessageElement(message));
    });
}

function createMessageElement(message) {
    let msgElement = document.createElement('div');
    msgElement.innerHTML =
        `<div class="message row" id="${message.id}">
                <h5 class="author col">${message.author.username}</h5>
                <div class="col">
                    <div class="row">
                        <div class="col">${message.dateTime}</div>
                        <div class="col text">${message.text}</div>
                    </div>
                </div>
             </div>
            `;
    return msgElement.children[0];
}
function addMessageElement(msgElement) {
    document.getElementsByClassName('chat')[0].append(msgElement);
}

async function message() {
    const messageForm = document.getElementsByClassName("messageForm")[0];
    await messageForm.addEventListener("submit", async function(e){
        e.preventDefault();
        const data = new FormData(messageForm);
        const messageJSON = JSON.stringify(Object.fromEntries(data));
        await fetch(restUrl+'/messages/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            body: messageJSON,

            cache: 'no-cache',
            mode : 'cors'
        });
    });
}

loadMessages();

setInterval(async function() {
    await loadMessages();} , 1000);

message();
