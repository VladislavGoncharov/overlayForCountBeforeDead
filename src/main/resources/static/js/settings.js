settingWidthOfButtons()


function updateNickname(id){
    const nickname = document.getElementById('inputForPlayer'+id);
    $.ajax({
        type: 'POST',
        url: 'settings/update-player-' + id + '-nickname-' + nickname.value,
        async: false
    });
    setTimeout(() => location.reload(),10)

}
function updateUserUsername(id){
    const username = document.getElementById('inputForUserUsername'+id);
    $.ajax({
        type: 'POST',
        url: 'settings/update-user-' + id + '-username-' + username.value,
        async: false
    });
}
function updateUserPassword(id){
    const password = document.getElementById('inputForUserPassword'+id);
    $.ajax({
        type: 'POST',
        url: 'settings/update-user-' + id + '-password-' + password.value,
        async: false
    });
}
function randomPassword(id){
    $.ajax({
        type: 'POST',
        url: 'settings/random-password-' + id,
        async: false
    });
    setTimeout(() => location.reload(),10)
}
function resetTime(id){
    $.ajax({
        type: 'POST',
        url: 'settings/reset-time-player-' + id,
        async: false
    });
    setTimeout(() => location.reload(),10)
}
function resetSelectedMap(){
    $.ajax({
        type: 'POST',
        url: 'settings/reset-selected-map',
        async: false
    });
}

function settingWidthOfButtons(){
    const width = document.getElementById('button reset time 1').offsetWidth
    const styleButton = document.querySelectorAll('.width-reset-time')

    styleButton.forEach(btn => {
        btn.style.width = width + 'px';

    })
}

function copyText(userId){
    $.ajax({
        type: 'POST',
        url: 'settings/get-user-data-by-id-' + userId,
        async: false,
        success(request) {
            const result = request.split('&&')

            navigator.clipboard.writeText('логин: ' + result[0] + '\nпароль: ' + result[1]);
        }
    });
}

function updateTimeCutoff(){
    let time = document.getElementById('timeCutoffInput');
    if (1000 <= time.value && time.value <= 15000 ) {
        $.ajax({
            type: 'POST',
            url: 'settings/timeCutoff=' + time.value,
            async: false
        });
        setTimeout(() => location.reload(),10)
    }
    else alert('Значение должно быть от 1000 до 15000')
}