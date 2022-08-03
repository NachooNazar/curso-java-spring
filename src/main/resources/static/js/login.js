// Call the dataTables jQuery plugin

async function loginUser() {

const data = {};

data.email = document.getElementById('email').value;
data.password = document.getElementById('password').value;

const req = await fetch("api/login", {
  method: "POST",
  headers: {
   'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
   body: JSON.stringify(data)
    });

    const res = await req.text();
    if(res !== null){
    localStorage.setItem('token', res);
    localStorage.email = data.email;

    window.location.href = "users.html"
    }else{
    alert("Invalid username or password")
    }
}