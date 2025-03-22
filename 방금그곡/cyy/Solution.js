// 연장 후 길이에 맞게 substring 
function adjustString(str, len) {
  return (str.repeat(Math.ceil(len / str.length))).substring(0, len);
}
// #을 소문자 변환
function detectSharp(string) {
    let result = '';
    let sharp = false;
    
    for (let i = string.length - 1; i >= 0; i--) {
        if (string[i] === "#") {
            sharp = true;
        } else {
            if (sharp) {
                result = string[i].toLowerCase() + result;
                sharp = false;
            } else {
                result = string[i] + result;
            }
        }
    }
    return result;
}

function solution(m, musicinfos) {
    m = detectSharp(m);
    let ans = "";
    let longest = 0;
    for (let musicinfo of musicinfos) {
        let arr = musicinfo.split(",");
        let startTime = arr[0].split(":");
        let startSec = parseInt(startTime[0]*60)+parseInt(startTime[1]);
        let endTime = arr[1].split(":");
        let endSec = parseInt(endTime[0]*60)+parseInt(endTime[1]);
        let musicLength = endSec - startSec;
        let code = detectSharp(arr[3]);
        let codePlayed = adjustString(code, musicLength);
        if (m.length <= musicLength) {
            if (codePlayed.includes(m)) {
                if (musicLength > longest) {
                    longest = musicLength
                    ans = arr[2];
                }
            }      
        }
        else {
            if (m.includes(code)) {
                if (musicLength > longest) {
                    longest = musicLength
                    ans = arr[2];
                }
            }
        }
    }
    if (ans.length === 0) ans = "(None)"
    return ans;
}
