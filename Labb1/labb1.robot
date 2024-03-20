*** Settings ***
Documentation  Boka bil
Library  SeleniumLibrary
Library    OperatingSystem
Suite Setup     setup
Suite Teardown  Tear Down

*** Variables ***
${url}          http://rental15.infotiv.net/
${title}     //*[@id="title"]
${e-mail field}     //*[@id="email"]
${pass field}       //*[@id="password"]
${login}    //*[@id="login"]
${firstname}    Atakan
${lastname}      Kinali
${phonenumber}      0739252896
${usermail}     kinaliatakan@hotmail.com
${pass}     Lamporna99
${start date}      03-20
${end date}        03-28
${continue}        //*[@id="continue"]
${car}          //*[@id="bookRoadsterpass2"]
${card no.}     1231231231231231
${card day}     0
${card month}    7
${cvc}          789
${confirm}      //*[@id="confirm"]
${mypage}       //*[@id="mypage"]
${logout}       //*[@id="logout"]

*** Test Cases ***
car booking
    User logs into website      ${usermail}     ${pass}
    User chooses date for booking
    User chooses car
    User enters card info
    User confirms booking
    testing cancel booking function


cancel booking
    testing cancel booking function
    
wrong account info
    wrong e-mail
    wrong password
    
    
short cardnumber
    testing short cardnumber        

*** Keywords ***
setup
    Open Browser    browser=Chrome
    Go To    ${url}
    Wait Until Page Contains Element    ${title}
    Set Selenium Speed    0.5

Tear Down
    Close Browser

User logs into website
    [Arguments]     ${username}     ${password}
    [Documentation]     User enters information to log in
    [Tags]      log in
    Input Text    //*[@id="email"]    ${usermail}
    Input Password    //*[@id="password"]    ${pass}
    Click Element    //*[@id="login"]
    Wait Until Page Contains Element    //*[@id="welcomePhrase"]
    
User chooses date for booking
    [Documentation]     User enters date for booking
    [Tags]      Date
    Click Element    //*[@id="start"]
    Click Element    //*[@id="end"]
    Input Text    //*[@id="end"]    ${end date}
    Click Element    //*[@id="continue"]

User chooses car
    [Documentation]     User chooses car
    [Tags]      Car
    Wait Until Page Contains Element    //*[@id="questionText"]
    Click Element    ${car}

User enters card info
    [Documentation]     User enters personal info
    [Tags]      info
    Wait Until Page Contains Element    //*[@id="questionText"]
    Input Text    //*[@id="cardNum"]    ${card no.}
    Input Text    //*[@id="fullName"]    ${firstname} ${lastname}
    Select From List By Index    //*[@id="confirmSelection"]/form/select[1]     ${card day}
    Select From List By Index    //*[@id="confirmSelection"]/form/select[2]     ${card month}
    Input Text    //*[@id="cvc"]    ${cvc}
    Click Element    //*[@id="confirm"]

User confirms booking
    [Documentation]     confirm booking
    [Tags]              booking
    Wait Until Page Contains Element    //*[@id="confirmMessage"]
    Click Element    //*[@id="mypage"]
    Wait Until Page Contains Element    //*[@id="historyText"]

testing cancel booking function
    [Documentation]     cancel booking function
    [Tags]      cancel
    Input Text    //*[@id="email"]    ${usermail}
    Input Password    //*[@id="password"]    ${pass}
    Click Element    //*[@id="login"]
    Wait Until Page Contains Element    //*[@id="welcomePhrase"]
    Click Element    //*[@id="start"]
    Click Element    //*[@id="end"]
    Input Text    //*[@id="end"]    ${end date}
    Click Element    //*[@id="continue"]
    Wait Until Page Contains Element    //*[@id="questionText"]
    Click Element    ${car}
    Wait Until Page Contains Element    //*[@id="questionText"]
    Input Text    //*[@id="cardNum"]    ${card no.}
    Input Text    //*[@id="fullName"]    ${firstname} ${lastname}
    Select From List By Index    //*[@id="confirmSelection"]/form/select[1]     ${card day}
    Select From List By Index    //*[@id="confirmSelection"]/form/select[2]     ${card month}
    Input Text    //*[@id="cvc"]    ${cvc}
    Click Element    //*[@id="confirm"]
    Wait Until Page Contains Element    //*[@id="confirmMessage"]
    Click Element    //*[@id="mypage"]
    Wait Until Page Contains Element    //*[@id="historyText"]
    Click Element    //*[@id="unBook1"]
    Handle Alert        Accept

wrong e-mail
    [Documentation]     Wrong e-mail 
    [Tags]      wrong e-mail 
    Input Text    //*[@id="email"]    wrong e-mail@hotmail.com
    Input Text    //*[@id="password"]    ${pass}
    Click Element    //*[@id="login"]
    Wait Until Page Contains Element    //*[@id="signInError"]
    
wrong password
    [Documentation]     Wrong password
    [Tags]      Wrong password
    Input Text    //*[@id="email"]    ${usermail}
    Input Text    //*[@id="password"]    hjkhjkhjk
    Click Element    //*[@id="login"]
    Wait Until Page Contains Element    //*[@id="signInError"]

testing short cardnumber
    [Documentation]     Short cardnumber
    [Tags]      Short cardnumber
    Input Text    //*[@id="email"]    ${usermail}
    Input Text    //*[@id="password"]    ${pass}
    Click Element    //*[@id="login"]
    Wait Until Page Contains Element    //*[@id="welcomePhrase"]
    Click Element    //*[@id="start"]
    Click Element    //*[@id="end"]
    Input Text    //*[@id="end"]    ${end date}
    Click Element    //*[@id="continue"]
    Wait Until Page Contains Element    //*[@id="questionText"]
    Click Element    ${car}
    Input Text    //*[@id="cardNum"]    123
    Input Text    //*[@id="fullName"]    ${firstname}   ${lastname}
    Select From List By Index    //*[@id="confirmSelection"]/form/select[1]     ${card day}
    Select From List By Index    //*[@id="confirmSelection"]/form/select[2]     ${card month}
    Input Text    //*[@id="cvc"]    ${cvc}
    Click Element    //*[@id="confirm"]
    Page Should Contain    Confirm booking of Tesla Roadster



    



    




    
