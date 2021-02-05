
var caster;
var hidden = false;
window.onload = async () => {
    var userid = document.getElementById("userid").value;
    var roomNumber = document.getElementById("roomNumber").value;

    const config = {
        credential: {
            serviceId: '4892bc68-6ab2-439b-bc94-a9d64cb4db64',
            key: '734497fae0f60d8909b59556511b7206c241b310cb31daac84007a79d1ab7d7e'
        },
        media: {
            video: true,
            audio: true,
        },
        view: {
            local: '#localVideo'
        }
    }
    var remonRoom = [];
    const listener = {
        onCreate(channelId) {
            myChannelId = channelId;
        },
        onComplete() {
            remonRoom[caster.getChannelId()] = true;
        },
        onRoomEvent(result) {
            let id = result.channel.id;
            console.log(id + ',,,,,,,,,,,,,,,');
            switch (result.event) {
                case 'join':
                    var p = document.createElement("video");
                    p.id = id.split(':')[1];
                    p.autoplay = true;
                    p.muted = true;
                    config.view.remote = '#' + p.id;
                    p.remon = new Remon({ config });
                    document.getElementsByClassName("video-zone")[0].append(p);
                    p.remon.joinCast(id);
                    break;
                case 'leave':
                    let video = document.getElementById(id.split(':')[1]);
                    if (video) document.getElementsByClassName("video-zone")[0].removeChild(video);
                    break;
            }
        },
        onMessage(message) {
            console.log(message);
        }

    }
    caster = new Remon({ config, listener })
    await caster.createCast();
    var viewers = {};
    await caster.createRoom("a" + roomNumber);
    var searchResult = await caster.fetchRooms("a" + roomNumber);
    setTimeout(() => {
        searchResult.forEach(async ({ id }, i) => {
            console.log(id + ', ' + i);
            var p = document.createElement("video");
            p.id = id.split(':')[1];
            p.autoplay = true;
            p.muted = true;
            config.view.remote = '#' + p.id;
            p.remon = new Remon({ config });
            document.getElementsByClassName("video-zone")[0].append(p);
            await p.remon.joinCast(id);
        })
    }, 1000);
    var socket = io();

    var messages = document.getElementById('chat-messages');
    var form = document.getElementById('chat-form');
    var input = document.getElementById('chat-input');

    form.addEventListener('submit', function (e) {
        e.preventDefault();
        console.log(`${roomNumber}:-:${userid} : ` + input.value);
        if (input.value) {
            socket.emit('chat message', `${roomNumber}:-:${userid} : ` + input.value);
            console.log(input.value);
            input.value = '';
        }
    });
    socket.on('chat message', function (msg) {
        var rn = msg.substr(0, msg.indexOf(":-:"));
        var nrn = roomNumber;
        if (rn != nrn) {
            console.log("www");
            return;
        }
        var realmsg = msg.substr(msg.indexOf(":-:") + 3, msg.length);
        var item = document.createElement('li');
        var mesid = realmsg.substr(0, realmsg.indexOf(" : "));
        var dummySrc = 'https://swhackathon.com/writable/uploads/1612294605_6a7cdedbb5143c80bb8d.png';

        realmsg = realmsg.substr(realmsg.indexOf(" : ") + 3, realmsg.length);
        if (mesid == userid) {
            item.classList.add("mychat");
            item.innerHTML = `
                <div class="text-wrapper">    
                <div class="real-text">${realmsg}</div>
                </div>    
                `
        }
        else {
            item.innerHTML = `
                <div class="profile-wrapper">    
                <img class="chat-profile" src="${dummySrc}">
                </div>
                <div class="text-wrapper">    
                <text class="chat-userid">${mesid}</text>
                <div class="real-text">${realmsg}</div>
                </div>    
                `
        }
        messages.appendChild(item);
        var objDiv = document.getElementById("chat-messages");
        objDiv.scrollTop = objDiv.scrollHeight;
        window.scrollTo(0, document.body.scrollHeight);
    });

    var bodyTag = document.getElementsByTagName("body")[0];
    var header = document.getElementsByTagName("header")[0];
    var footer = document.getElementsByTagName("footer")[0];
    var peopleAside = document.getElementById("side-people");
    var chatAside = document.getElementById("side-chat");
    var albumAside = document.getElementById("side-album");
    var settingAside = document.getElementById("side-setting");
    var asiders = [peopleAside, chatAside, albumAside, settingAside];

    var timer = 0;
    bodyTag.addEventListener("click", (e) => {
        timer = 0;
        if (hidden) {
            header.classList.remove("hidden");
            footer.classList.remove("hidden");
        }
        else {
            header.classList.add("hidden");
            footer.classList.add("hidden");
            asiders.forEach((item) => { item.classList.add("hidden"); });
        }
        hidden = !hidden;
    })

    asiders.forEach((aside) => {
        aside.addEventListener("click", (e) => {
            e.stopPropagation();
        })
    })

    header.addEventListener("click", (e) => { e.stopPropagation() });
    footer.addEventListener("click", (e) => { e.stopPropagation() });

    var lastLeftSide = "";

    var peopleButton = document.getElementById("people");
    var chatButton = document.getElementById("chat");
    var albumButton = document.getElementById("album");
    var settingButton = document.getElementById("setting");
    var buttons = [peopleButton, chatButton, albumButton, settingButton];
    buttons.forEach((button) => {
        button.addEventListener("click", (e) => {
            e.stopPropagation();
            footer.classList.add("hidden");
            var prev = document.getElementById(lastLeftSide);
            console.log(prev);
            console.log(lastLeftSide);
            if (prev) prev.classList.add("hide");
            lastLeftSide = "side-" + button.id;
            document.getElementById(lastLeftSide).classList.remove("hide");
            document.getElementById(lastLeftSide).classList.remove("hidden");
        })
    })
    setInterval(() => {
        timer += 100;
        if (timer > 5000) {
            header.classList.add("hidden");
            footer.classList.add("hidden");
            asiders.forEach((item) => { item.classList.add("hidden"); });
            hidden = true;
        }
    }, 100);

}