// Call the dataTables jQuery plugin

async function registerUser() {
console.log("qwdqw")

const data = {};

data.name = document.getElementById('firstname').value;
data.lastname = document.getElementById('lastname').value;
data.email = document.getElementById('email').value;
data.phone = "12223334";
if(document.getElementById('password').value !== document.getElementById('passwordVerify').value){
alert("pass has to be equals")
return;
}
data.password = document.getElementById('password').value;

await fetch("api/users", {
  method: "POST",
  headers: {
   'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
   body: JSON.stringify(data)
    });

    alert("registrado god");
    window.location.href = "login.html"
}
