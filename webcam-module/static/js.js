
var caster;
var hidden = false;
window.onload = async () => {
    var userid = document.getElementById("userid").value;
    var roomNumber = document.getElementById("roomNumber").value;
    var localvideo = document.getElementsByTagName("video")[0];

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
        item.textContent = realmsg;
        messages.appendChild(item);
        window.scrollTo(0, document.body.scrollHeight);
    });

    var bodyTag = document.getElementsByTagName("body")[0];
    var header = document.getElementsByTagName("header")[0];
    var footer = document.getElementsByTagName("footer")[0];

    bodyTag.addEventListener("click", (e) => {
        if (hidden) {
            header.classList.remove("hidden");
            footer.classList.remove("hidden");
        }
        else {
            header.classList.add("hidden");
            footer.classList.add("hidden");
        }
        hidden = !hidden;
    })
}