function selectMap(idMap, idUser) {
    $.ajax({
        type: 'POST',
        url: 'switch-map/map-select-' + idMap + '-user-' + idUser,
        async: false
    });
}

function getNameOfPlayerWhoChose(mapId) {
    mapId++;
    let playerName = ""
    $.ajax({
        type: 'POST',
        url: '/switch-map/get-name-of-player-by-map-id-' + mapId,
        async: false,
        success: function (request) {
            playerName = request
        }
    })

    return playerName;

}

let interval = setInterval(updateSelectMap, 300)

function updateSelectMap() {
    $.ajax({
        type: 'POST',
        url: '/switch-map/get-all-is-selected',
        async: false,
        success: function (request) {
            const arrayIsSelected = request;
            let numberOfSelected = 0;
            for (let i = 0; i < arrayIsSelected.length; i++) {

                let mapSelect = document.getElementById('map select ' + i)
                let playerNameWhoChooseMap = document.getElementById('map select player name ' + i)
                mapSelect.style.opacity = '1';
                playerNameWhoChooseMap.style.opacity = '1';


                if (arrayIsSelected[i]) {
                    mapSelect.style.opacity = '1';
                    playerNameWhoChooseMap.innerText = getNameOfPlayerWhoChose(i);
                    playerNameWhoChooseMap.style.opacity = '1';

                    numberOfSelected++;
                } else {
                    mapSelect.style.opacity = '0';
                    playerNameWhoChooseMap.innerText = '-';
                    playerNameWhoChooseMap.style.opacity = '0';
                }
            }

            if (++numberOfSelected === arrayIsSelected.length) {
                for (let i = 0; i < arrayIsSelected.length; i++) {
                    if (!arrayIsSelected[i]) {
                        document.getElementById('divSelect' + i).style.transform = ('scale(1.3)');
                        document.getElementById('map select player name ' + i).style.opacity = '0';
                    } else document.getElementById('divSelect' + i).style.transform = ('scale(0.8)');
                }
                clearInterval(interval);
                interval = setInterval(resetMap, 500)
            }
        }
    })
}

function resetMap() {
    $.ajax({
        type: 'POST',
        url: '/switch-map/is-reset-map',
        async: false,
        success: function (request) {
            if (request) {
                clearInterval(interval);

                $.ajax({
                    type: 'POST',
                    url: '/switch-map/get-all-is-selected',
                    async: false,
                    success: function (request) {
                        for (let i = 0; i < request.length; i++) {
                            document.getElementById('divSelect' + i).style.transform = ('scale(1)');
                            document.getElementById('map select ' + i).style.opacity = '0';
                            document.getElementById('map select player name ' + i).style.opacity = '0';
                        }
                    }

                })
                setTimeout(() => interval = setInterval(updateSelectMap, 300),1500)
            }
        }
    })
}