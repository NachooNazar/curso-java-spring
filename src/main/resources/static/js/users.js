// Call the dataTables jQuery plugin
$(document).ready(function() {
    loadUsers();
  $('#users').DataTable();
});

function getHeaders() {
return {
          'Accept': 'application/json',
             'Content-Type': 'application/json',
             'Authorization': localStorage.token
             }
}

async function loadUsers() {

const response = await fetch("api/users",{
  method: "GET",
  headers: getHeaders()
  });

    const users = await response.json();
 console.log(users,'soy users');
    let listUsers = '';
    if(users.length === 0){
    listUsers = '<h3>No users found</h3>';
    }

    for (let us of users) {
     let brnDelete = `<a href="#" onClick="deleteUser(${us.id})" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>`;
    const user =`<tr><td>${us.id}</td><td>${us.name}</td><td>${us.email}</td><td>${us.phone}</td><td>${brnDelete}</td></tr>`;
    listUsers += user;
    }

 document.querySelector('#users tbody').outerHTML = listUsers;
}

async function deleteUser(id) {
if(confirm('Are you sure you want to delete')){
await fetch(`api/users/${id}`,{
  method: "DELETE",
  headers: getHeaders()
  });
}

location.reload();
}