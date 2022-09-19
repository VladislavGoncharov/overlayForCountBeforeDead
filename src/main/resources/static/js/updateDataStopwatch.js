showOverlay()
setInterval(function () {
        getTimePlayer(1)
        getTimePlayer(2)
        showOverlay()
}, 500);

function getTimePlayer(playerId) {
    $.ajax({
        type: 'POST',
        url: 'get-player/' + playerId,
        async: false,
        success: function (request) {
            const arrayPlayer = request
            document.getElementById('player_nickname_id_' + playerId).innerText = arrayPlayer[0];
            document.getElementById('player_time_id_' + playerId).innerText = arrayPlayer[1];
        }
    });
}

function switcherOverlay() {
    $.ajax({
        type: 'POST',
        url: 'switcher-overlay',
        async: false
    });
}

function showOverlay() {
    $.ajax({
        type: 'POST',
        url: 'get-switcher-overlay',
        async: false,
        success: function (request) {
            if (request === 'none') {
                document.getElementById('container').style.display = 'none';
                document.getElementById('button hide').style.display = 'none';
                document.getElementById('button show').style.display = 'block';
            } else {
                document.getElementById('container').style.display = 'block';
                document.getElementById('button hide').style.display = 'block';
                document.getElementById('button show').style.display = 'none';
            }
        }
    });
}


