
function setOffsetImgPicturesNickname(){
    const offsetNickname = document.getElementById('offsetImgUnderNicknameInput');
    $.ajax({
        type: 'POST',
        url: 'others-pictures/saveOffsetNickname=' + offsetNickname.value,
        async: false
    });
}
function setOffsetImgPicturesStopwatch(){
    const offsetStopwatch = document.getElementById('offsetImgUnderStopwatchInput');
    $.ajax({
        type: 'POST',
        url: 'others-pictures/saveOffsetStopwatch=' + offsetStopwatch.value,
        async: false
    });
}
