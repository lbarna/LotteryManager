<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lottery manager</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <h1>Welcome to Lottery Manager!</h2>

    <h2>Highest prize so far</h2>
    <p>${highestPrize} Ft<p>

    <h2>Total winners so far</h2>
    <p>${totalWinners} (people)<p>

    <h2>Win percentage</h2>
    <p>${winPerc}%<p>

    <h2>Highest win share count so far</h2>
    <p>${winShare} (people)<p>

    <h2>Previous most frequent winner numbers</h2>
    <table>
        <tr>
            <th>Winner number</th>
            <th>Count</th>
        </tr>
        <#assign mfn = mostFrequentNumbers!{} />
        <#list mfn?keys as number>
            <tr>
                <td>${number}</td>
                <td>${mfn[number]}</td>
            </tr>
        </#list>
    <table>


    <h2>Previous winner numbers</h2>

    <table>
        <tr>
            <th>Year</th>
            <th>Date</th>
            <th>Prize</th>
            <th>Hit</th>
            <th>Numbers</th>
        </tr>
        <#list winnerNumbers as number>
            <tr>
                <td>${(number.year)!""}</td>
                <td>${(number.date)!}</td>
                <td>${(number.prize)!"0 Ft"}</td>
                <td>${(number.winnersCount)!}</td>
                <td>${number.numbers[0]},${number.numbers[1]},${number.numbers[2]},${number.numbers[3]},${number.numbers[4]}</td>
            </tr>
        </#list>
    <table>
</body>
</html>