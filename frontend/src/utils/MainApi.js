import app from "../components/App/App";

export const BASE_URL = 'http://localhost:9191';
export const MAIN_BACKEND = 'http://localhost:8181'

export const getAllWaitReportApp = (username, setApps) => {
    var details = {
        'status': 'report-waiting',
        'username': localStorage.getItem("username")
    };
    var formBody = [];
    for (var property in details) {
        var encodedKey = encodeURIComponent(property);
        var encodedValue = encodeURIComponent(details[property]);
        formBody.push(encodedKey + "=" + encodedValue);
    }
    formBody = formBody.join("&");
    return fetch(`${MAIN_BACKEND}/api/applications`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formBody
    })
        .then(checkResponse)
        .then((data) => setApps(data))
}
export const getAllApplicationsByUser = (username, setRequests) => {
    return fetch(`${MAIN_BACKEND}/api/applications/` + username, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(checkResponse)
        .then((data) => setRequests(data))
}

export const approveByLead = (appId) => {
    return fetch(`${MAIN_BACKEND}/api/approve/lead/` + appId, {
        method: "GET",
    })
        .then(checkResponse)
}

export const approveByAccountant = (appId) => {
    return fetch(`${MAIN_BACKEND}/api/approve/accountant/` + appId, {
        method: "GET"
    })
        .then(checkResponse)
}

export const getAllAppsForLeadApprove = (username, setApps) => {
    var details = {
        'status': 'not approved',
        'master': localStorage.getItem("username")
    };
    var formBody = [];
    for (var property in details) {
        var encodedKey = encodeURIComponent(property);
        var encodedValue = encodeURIComponent(details[property]);
        formBody.push(encodedKey + "=" + encodedValue);
    }
    formBody = formBody.join("&");
    return fetch(`${MAIN_BACKEND}/api/applications/master`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formBody
    })
        .then(checkResponse)
        .then((data) => setApps(data))
}

export const getAllApplicationForAccountantApprove = (setApp) => {
    return fetch(`${MAIN_BACKEND}/api/accountant`, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(checkResponse)
        .then((data) => setApp(data))
}

export const getApplicationById = (id, setApp) => {
    return fetch(`${MAIN_BACKEND}/api/application/` + id, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
        }
    }).then(checkResponse)
        .then((data) => setApp(data))
}

export const getAccountantApplicationById = (id, setApp) => {
    return fetch(`${MAIN_BACKEND}/api/accountant/` + id, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(checkResponse)
        .then((data) => setApp(data))
}

export const getLeadApplicationById = (id, setApp) => {
    return fetch(`${MAIN_BACKEND}/api/lead/` + id, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(checkResponse)
        .then((data) => setApp(data))
}


function checkResponse(res) {
    if (res.ok) {
        return res.json();
    }
    return Promise.reject(`Ошибка: ${res}`);
}



export const getInfo = (token) => {
    return fetch(`${BASE_URL}/users/me`, {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        }
    })
        .then(checkResponse);
}

export const getToken = () => {
    var details = {
        'grant_type': 'client_credentials',
        'scope': 'openid'
    };

    var formBody = [];
    for (var property in details) {
        var encodedKey = encodeURIComponent(property);
        var encodedValue = encodeURIComponent(details[property]);
        formBody.push(encodedKey + "=" + encodedValue);
    }
    formBody = formBody.join("&");
    return fetch(`${BASE_URL}/oauth2/token`, {
        method: 'POST',
        headers: {
            'Authorization': 'Basic ' + 'ZnJvbnQ6dGVzdA==',
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formBody
    })
        .then(checkResponse)
        .then((data) => {
            if (data.access_token) {
                localStorage.setItem('token', data.access_token);
                return data;
            }
        })
}
export const getLoginUserInfo = (username) => {
    return fetch(`${MAIN_BACKEND}/api/employee/` + username, {
        method: 'GET'
    })
        .then(checkResponse)
        .then((data) => {
            if (data) {
                localStorage.setItem("name", data.name)
                localStorage.setItem("surname", data.surname)
                localStorage.setItem("patronymic", data.patronymic)
                localStorage.setItem("phone", data.phone)
                localStorage.setItem("email", data.email)
                localStorage.setItem("position", data.position)
                localStorage.setItem("role", data.role)
                return data
            }
        })
}

export const getUserInfo = (username, setData) => {
    return fetch(`${MAIN_BACKEND}/api/employee/` + username, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(checkResponse)
        .then((data) => {setData(data)})
}

export const sendAppForm = (body) => {
    return fetch(`${MAIN_BACKEND}/api/application`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: body
    }).then(checkResponse)
}

export const authorize = (username, password) => {
    return fetch(`${BASE_URL}/api/auth`, {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token'),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "username": username,
            "password": password
        })
    })
        .then(checkResponse)
        .then((data) => {
            if (data.status === 'ACCESS_ACCEPTED') {
                localStorage.setItem('username', username)
                return data;
            } else {
                return Promise.reject(`Ошибка: Неверный логин или пароль`)
            }
        })
}

export const checkToken = (token) => {
    return fetch(`${BASE_URL}/api/auth`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        }
    })
        .then(checkResponse);
}

export const setUserInfo = (name, email, token) => {
    return fetch(`${BASE_URL}/users/me`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`,
        },
        body: JSON.stringify({
            name: name,
            email: email,
        })
    })
        .then(checkResponse);
}


export const getStatus = (status) => {
    if (status === 'not approved') {
        return 'На согласовании у руководителя'
    } else if (status === 'approved by master') {
        return 'Согласовано руководителем'
    } else if (status === 'approved by accountant') {
        return 'На согласовании бухгалетрии'
    } else if (status === 'report-waiting') {
        return 'Ожидает отчета'
    } else if (status === 'archived') {
        return 'Завершено'
    } else {
        return 'Ошибка. Невалидной состояние'
    }
}
