export const BASE_URL = 'http://localhost:9191';
export const MAIN_BACKEND = 'http://localhost:8181'

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
 export const getUserInfo = (username) => {
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

export const authorize  = (username, password) => {
  return fetch(`${BASE_URL}/api/auth`, {
    method: 'POST',
    headers: {
      'Authorization': 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        "username": username,
        "password": password })
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
  return fetch(`${BASE_URL}/users/me`, {
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

