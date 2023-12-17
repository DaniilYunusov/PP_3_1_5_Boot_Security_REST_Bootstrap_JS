async function theModal(form, modal, id){
    modal.show();
    let user = await getUserId(id);
    form.id.value = user.id;
    form.name.value = user.name;
    form.age.value = user.age;
    form.email.value = user.email;
    form.roles.value = user.roles;
}