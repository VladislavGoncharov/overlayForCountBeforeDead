const secondElement = document.getElementById('second');
const millisecondElement = document.getElementById('millisecond');
const audio = document.getElementById('audio')
audio.load()

let second = 0,
    millisecond = 0

let interval = null;
let intervalStopwatchForAll = null;
let intervalStartStopwatch = null;

function start() {
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

nextStep()

function nextStep() {
    setTimeout(() => {
        $.ajax({
            type: 'POST',
            url: 'does-stopwatch-work',
            async: false,
            success(request) {
                console.log(request)
                if (request === 'START' && intervalStartStopwatch == null) startStopwatch();
                else if (request === 'STOP') stopStopwatch();
                else if (request === 'RESET') resetStopwatch();
            }
        });
    }, 80)
}

function startStopwatch() {
    resetStopwatch()

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

    intervalStartStopwatch = setTimeout(() => {
        if (interval == null) {
            interval = setInterval(start, 10);
        }
    }, timeCutoff)

    intervalStopwatchForAll = setInterval(nextStep, 100)
}

function stopStopwatch() {
    clearInterval(interval)
    clearInterval(intervalStopwatchForAll)
    clearTimeout(intervalStartStopwatch)
    innerTextResult()

    interval = null
    intervalStopwatchForAll = null
    intervalStartStopwatch = null

    audioStop()

    setTimeout(nextStep, 100)
}

function resetStopwatch() {
    clearInterval(interval)
    clearInterval(intervalStopwatchForAll)
    clearTimeout(intervalStartStopwatch)

    second = 0
    millisecond = 0
    secondElement.innerText = "00";
    millisecondElement.innerText = "00";

    interval = null
    intervalStopwatchForAll = null
    intervalStartStopwatch = null

    audioStop()
    setTimeout(nextStep, 100)
}

function innerTextResult() {
    $.ajax({
        type: 'POST',
        url: 'get-result-stopwatch',
        async: false,
        success(request) {
            const result = request.split(':')

            second = parseInt(result[0])
            millisecond = parseInt(result[1])
        }
    });

    if (second < 10) second = "0" + second;
    secondElement.innerText = "" + second;

    if (millisecond < 10) millisecond = "0" + millisecond;
    millisecondElement.innerText = "" + millisecond;
}

function audioStop() {
    audio.pause()
    audio.currentTime = 0
}
