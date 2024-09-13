document.addEventListener('DOMContentLoaded', function () {
    // Eventos de carga inicial
    loadOdontologos();
    loadPacientes();
    loadTurnos();
    loadSelectOptions();

    // Asignar eventos a formularios
    const odontologoForm = document.getElementById('odontologoForm');
    const pacienteForm = document.getElementById('pacienteForm');
    const turnoForm = document.getElementById('turnoForm');

    if (odontologoForm) odontologoForm.addEventListener('submit', submitOdontologo);
    if (pacienteForm) pacienteForm.addEventListener('submit', submitPaciente);
    if (turnoForm) turnoForm.addEventListener('submit', submitTurno);
});

// Cargar lista de odontólogos
function loadOdontologos() {
    fetch('/api/odontologos')
        .then(response => response.json())
        .then(data => {
            const odontologosTable = document.querySelector('#odontologos tbody');
            odontologosTable.innerHTML = '';
            data.forEach(odontologo => {
                odontologosTable.innerHTML += `
                    <tr>
                        <td>${odontologo.apellido}</td>
                        <td>${odontologo.nombre}</td>
                        <td>${odontologo.matricula}</td>
                        <td><button onclick="deleteOdontologo(${odontologo.id})">Eliminar</button></td>
                    </tr>`;
            });
        });
}

// Cargar lista de pacientes
function loadPacientes() {
    fetch('/api/pacientes')
        .then(response => response.json())
        .then(data => {
            const pacientesTable = document.querySelector('#pacientes tbody');
            pacientesTable.innerHTML = '';
            data.forEach(paciente => {
                pacientesTable.innerHTML += `
                    <tr>
                        <td>${paciente.nombre}</td>
                        <td>${paciente.apellido}</td>
                        <td>${paciente.dni}</td>
                        <td>${paciente.domicilio}</td>
                        <td>${paciente.fechaAlta}</td>
                        <td><button onclick="deletePaciente(${paciente.id})">Eliminar</button></td>
                    </tr>`;
            });
        });
}

// Cargar lista de turnos
function loadTurnos() {
    fetch('/api/turnos')
        .then(response => response.json())
        .then(data => {
            const turnosTable = document.querySelector('#turnos tbody');
            turnosTable.innerHTML = '';
            data.forEach(turno => {
                turnosTable.innerHTML += `
                    <tr>
                        <td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
                        <td>${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
                        <td>${turno.fecha}</td>
                        <td>${turno.hora}</td>
                        <td><button onclick="deleteTurno(${turno.id})">Eliminar</button></td>
                    </tr>`;
            });
        });
}

// Cargar opciones en select de turnos
function loadSelectOptions() {
    const pacienteSelect = document.querySelector('select[name="pacienteId"]');
    const odontologoSelect = document.querySelector('select[name="odontologoId"]');

    fetch('/api/pacientes')
        .then(response => response.json())
        .then(data => {
            pacienteSelect.innerHTML = '';
            data.forEach(paciente => {
                pacienteSelect.innerHTML += `<option value="${paciente.id}">${paciente.nombre} ${paciente.apellido}</option>`;
            });
        });

    fetch('/api/odontologos')
        .then(response => response.json())
        .then(data => {
            odontologoSelect.innerHTML = '';
            data.forEach(odontologo => {
                odontologoSelect.innerHTML += `<option value="${odontologo.id}">${odontologo.nombre} ${odontologo.apellido}</option>`;
            });
        });
}

// Agregar odontólogo
function submitOdontologo(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    fetch('/api/odontologos', {
        method: 'POST',
        body: formData
    }).then(() => {
        event.target.reset();
        loadOdontologos();
    });
}

// Agregar paciente
function submitPaciente(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    fetch('/api/pacientes', {
        method: 'POST',
        body: formData
    }).then(() => {
        event.target.reset();
        loadPacientes();
    });
}

// Agregar turno
function submitTurno(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    fetch('/api/turnos', {
        method: 'POST',
        body: formData
    }).then(() => {
        event.target.reset();
        loadTurnos();
    });
}

// Eliminar odontólogo
function deleteOdontologo(id) {
    fetch(`/api/odontologos/${id}`, {
        method: 'DELETE'
    }).then(() => loadOdontologos());
}

// Eliminar paciente
function deletePaciente(id) {
    fetch(`/api/pacientes/${id}`, {
        method: 'DELETE'
    }).then(() => loadPacientes());
}

// Eliminar turno
function deleteTurno(id) {
    fetch(`/api/turnos/${id}`, {
        method: 'DELETE'
    }).then(() => loadTurnos());
}
