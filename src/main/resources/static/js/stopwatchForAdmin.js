const secondElement = document.getElementById('second');
const millisecondElement = document.getElementById('millisecond');

const startButton = document.getElementById('button start')
const stopButton = document.getElementById('button stop')
const resetButton = document.getElementById('button reset')
const stopConfirmation = document.getElementById('button stop confirmation')
const buttonSendResultPlayer1 = document.getElementById('button send result player 1')
const buttonSendResultPlayer2 = document.getElementById('button send result player 2')
const audio = document.getElementById('audio')
audio.load()

let second = 0,
    millisecond = 0,
    finalSecond,
    finalMillisecond,
    playerTime;

let intervalStopwatch, intervalStartStopwatch;

function startStopwatch() {
    millisecond++;
    if (millisecond < 10) millisecondElement.innerText = "0" + millisecond;
    if (millisecond === 100) millisecondElement.innerText = "00";
    else millisecondElement.innerText = "" + millisecond;

    if (millisecond > 99) {
        millisecond = 0;
        second++
    }

    if (second < 10) secondElement.innerText = "0" + second;
    else secondElement.innerText = "" + second;
}

startButton.addEventListener('click', () => {
    resetButton.click()

    let timeCutoff;

    $.ajax({
        type: 'POST',
        url: 'get-time-cutoff-on-audio',
        async: false,
        success: function (request) {
            timeCutoff = parseInt(request)
        }
    })

    audio.play()

    setTimeout(() => {
        $.ajax({
            type: 'POST',
            url: 'stopwatch-start',
            async: false,
        });
    }, 100)


    intervalStartStopwatch = setTimeout(() => {
        if (intervalStopwatch == null) {
            intervalStopwatch = setInterval(startStopwatch, 10);
        }
    }, timeCutoff)

})

stopButton.addEventListener('click', () => {

    if (second === 0 && millisecond === 0) {
        resetButton.click()
    } else {
        finalSecond = second;
        finalMillisecond = millisecond;

        stopConfirmation.disabled = true
        stopConfirmation.style.display = 'block'

        setTimeout(() => {
            stopConfirmation.disabled = false
        }, 3000)
        setTimeout(() => {
            stopConfirmation.style.display = 'none'
        }, 10000)
    }
})

resetButton.addEventListener('click', () => {
    audioStop()
    clearInterval(intervalStopwatch)
    intervalStopwatch = null
    clearTimeout(intervalStartStopwatch)
    intervalStartStopwatch = null
    resetToZero()


    $.ajax({
        type: 'POST',
        url: 'stopwatch-reset',
        async: false,
    });


    buttonSendResultPlayer1.style.display = 'none'
    buttonSendResultPlayer2.style.display = 'none'
    stopConfirmation.style.display = 'none'
})

stopConfirmation.addEventListener('click', () => {
    clearInterval(intervalStopwatch)
    intervalStopwatch = null

    if (finalSecond < 10) finalSecond = "0" + finalSecond;
    secondElement.innerText = "" + finalSecond;

    if (finalMillisecond < 10) finalMillisecond = "0" + finalMillisecond;
    millisecondElement.innerText = "" + finalMillisecond;

    playerTime = finalSecond + ":" + finalMillisecond;

    buttonSendResultPlayer1.style.display = ''
    buttonSendResultPlayer2.style.display = ''
    stopConfirmation.style.display = 'none'

    $.ajax({
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        url: 'stopwatch-stop',
        data: JSON.stringify(playerTime),
    });

})


function sendResultPlayer(playerId) {
    $.ajax({
        type: "GET",
        url: '/' + playerId + '/time=' + playerTime,
        async: false,
        cache: false,
        processData: false,
    });

    buttonSendResultPlayer1.style.display = 'none'
    buttonSendResultPlayer2.style.display = 'none'

    resetButton.click();
}

function audioStop() {
    audio.pause()
    audio.currentTime = 0
}


function resetToZero() {
    second = 0
    millisecond = 0
    secondElement.innerText = "00";
    millisecondElement.innerText = "00";
}
