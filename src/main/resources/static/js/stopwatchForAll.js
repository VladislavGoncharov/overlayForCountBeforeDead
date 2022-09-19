const secondElement = document.getElementById('second');
const millisecondElement = document.getElementById('millisecond');

let second = 0,
    millisecond = 0

let interval = null;

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

let intervalStopwatchForAll

nextStep()

function nextStep() {
    setTimeout(() => {
        $.ajax({
            type: 'POST',
            url: 'does-stopwatch-work',
            async: false,
            success(request) {
                if (request === 'START' && interval == null) startStopwatch();
                else if (request === 'STOP') stopStopwatch();
                else if (request === 'RESET') resetStopwatch();
            }
        });
    }, 100)
}

function startStopwatch() {
    clearInterval(intervalStopwatchForAll)
    clearInterval(interval)

    second = 0
    millisecond = 0

    interval = setInterval(start, 10)
    intervalStopwatchForAll = setInterval(nextStep, 100)

}

function stopStopwatch() {
    clearInterval(interval)
    clearInterval(intervalStopwatchForAll)
    innerTextResult()

    interval = null
    nextStep()
}

function resetStopwatch() {
    clearInterval(interval)
    clearInterval(intervalStopwatchForAll)

    secondElement.innerText = "00";
    millisecondElement.innerText = "00";

    interval = null
    nextStep()
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