
const API_URL = "http://localhost:8080/api/tasks";
const USER_API_URL = "http://localhost:8080/api/users";
// função para criar usuário
async function createUser() {
    const name = document.getElementById("userName").value.trim();
    const email = document.getElementById("userEmail").value.trim();

    if (!name) {
        showNotification('Please enter a user name', 'error');
        return;
    }
    if (!email || !validateEmail(email)) {
        showNotification('Please enter a valid email', 'error');
        return;
    }

    const user = { name, email };

    try {
        const response = await fetch(USER_API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(user)
        });
        if (!response.ok) {
            throw new Error('Failed to create user');
        }

        const createdUser = await response.json();

        document.getElementById("userName").value = "";
        document.getElementById("userEmail").value = "";
        showNotification(`User created successfully! ID: ${createdUser.id}`, 'success');
        loadUsers();
    } catch (error) {
        console.error('Error creating user:', error);
        showNotification('Error creating user', 'error');
    }
}

// função para deletar usuário
async function deleteUser(userId) {
    if (!confirm(`Are you sure you want to delete user ID ${userId}?`)) {
        return;
    }

    try {
        const response = await fetch(`${USER_API_URL}/${userId}`, {
            method: "DELETE"
        });
        
        if (!response.ok) {
            throw new Error('Failed to delete user');
        }

        showNotification('User deleted successfully!', 'success');
        loadUsers();
    } catch (error) {
        console.error('Error deleting user:', error);
        showNotification('Error deleting user', 'error');
    }
}

// função para carregar usuários
async function loadUsers() {
    try {
        const response = await fetch(USER_API_URL);
        const users = await response.json();

        const list = document.getElementById("userList");
        const countElement = document.getElementById("userCount");
        
        countElement.textContent = `${users.length} user${users.length !== 1 ? 's' : ''}`;
        
        list.innerHTML = "";

        if (users.length === 0) {
            list.innerHTML = `
                <div class="empty-state">
                    <div class="empty-state-icon">👥</div>
                    <div class="empty-state-text">No users yet. Create your first user above!</div>
                </div>
            `;
            return;
        }

        users.forEach(user => {
            const userElement = document.createElement("div");
            userElement.className = "user-item";
            
            userElement.innerHTML = `
                <div class="user-info">
                    <div class="user-name">👤 ${escapeHtml(user.name)}</div>
                    <div class="user-email">✉️ ${escapeHtml(user.email)}</div>
                    <div class="user-id">ID: ${user.id}</div>
                </div>
                <button class="btn-delete" onclick="deleteUser(${user.id})">🗑️ Delete</button>
            `;
            
            list.appendChild(userElement);
        });
    } catch (error) {
        console.error('Error loading users:', error);
        showNotification('Error loading users', 'error');
    }
}

// função simples para validar email
function validateEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

// ordem de prioridade para ordenação
const PRIORITY_ORDER = {
    'HIGH': 1,
    'MEDIUM': 2,
    'LOW': 3
};

const PRIORITY_LABELS = {
    'HIGH': '🔴 High',
    'MEDIUM': '🟡 Medium',
    'LOW': '🟢 Low'
};

async function loadTasks() {
    try {
        const response = await fetch(API_URL);
        const tasks = await response.json();

        // ordenar tarefas por prioridade (High > Medium > Low)
        const sortedTasks = tasks.sort((a, b) => {
            return PRIORITY_ORDER[a.priority] - PRIORITY_ORDER[b.priority];
        });

        const list = document.getElementById("taskList");
        const countElement = document.getElementById("taskCount");
        
        // atualizar contador
        countElement.textContent = `${tasks.length} task${tasks.length !== 1 ? 's' : ''}`;
        
        list.innerHTML = "";

        if (sortedTasks.length === 0) {
            list.innerHTML = `
                <div class="empty-state">
                    <div class="empty-state-icon">📭</div>
                    <div class="empty-state-text">No tasks yet. Create your first task above!</div>
                </div>
            `;
            return;
        }

        sortedTasks.forEach(task => {
            const taskElement = document.createElement("div");
            taskElement.className = `task-item priority-${task.priority}`;
            
            taskElement.innerHTML = `
                <div class="task-header">
                    <div class="task-title">${escapeHtml(task.title)}</div>
                    <div class="task-priority">${PRIORITY_LABELS[task.priority]}</div>
                </div>
                ${task.description ? `<div class="task-description">${escapeHtml(task.description)}</div>` : ''}
                <div class="task-footer">
                    <div class="task-user">👤 ${escapeHtml(task.userName)}</div>
                    <div class="task-status">${task.status}</div>
                </div>
                <button class="btn-delete" onclick="deleteTask(${task.id})">🗑️ Delete</button>
            `;
            
            list.appendChild(taskElement);
        });
    } catch (error) {
        console.error('Error loading tasks:', error);
        showNotification('Error loading tasks', 'error');
    }
}

// função para deletar tarefa
async function deleteTask(taskId) {
    if (!confirm(`Are you sure you want to delete this task?`)) {
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${taskId}`, {
            method: "DELETE"
        });
        
        if (!response.ok) {
            throw new Error('Failed to delete task');
        }

        showNotification('Task deleted successfully!', 'success');
        loadTasks();
    } catch (error) {
        console.error('Error deleting task:', error);
        showNotification('Error deleting task', 'error');
    }
}

async function createTask() {
    const title = document.getElementById("title").value.trim();
    const description = document.getElementById("description").value.trim();
    const priority = document.getElementById("priority").value;
    const userId = document.getElementById("userId").value;

    // validação
    if (!title) {
        showNotification('Please enter a title', 'error');
        return;
    }

    if (!priority) {
        showNotification('Please select a priority', 'error');
        return;
    }

    if (!userId || userId < 1) {
        showNotification('Please enter a valid User ID', 'error');
        return;
    }

    const task = {
        title: title,
        description: description,
        priority: priority,
        userId: Number(userId)
    };

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(task)
        });

        if (!response.ok) {
            throw new Error('Failed to create task');
        }

        // limpar formulário
        document.getElementById("title").value = "";
        document.getElementById("description").value = "";
        document.getElementById("priority").value = "";
        document.getElementById("userId").value = "";

        showNotification('Task created successfully!', 'success');
        loadTasks();
    } catch (error) {
        console.error('Error creating task:', error);
        showNotification('Error creating task', 'error');
    }
}

// dunção para escapar HTML e prevenir XSS
function escapeHtml(text) {
    const map = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;'
    };
    return text.replace(/[&<>"']/g, m => map[m]);
}

// função para mostrar notificações
function showNotification(message, type = 'info') {
    // implementação simples com alert - pode ser melhorada com toast notifications
    if (type === 'error') {
        alert('❌ ' + message);
    } else if (type === 'success') {
        alert('✅ ' + message);
    } else {
        alert(message);
    }
}

// Definir cor de fundo cinza escuro
//document.body.style.backgroundColor = '#2c2c2c';
// Sistema de Temas
function setTheme(theme) {
    // Remover todas as classes de tema
    document.body.classList.remove('theme-classic', 'theme-light', 'theme-dark');
    
    // Adicionar a classe do tema selecionado
    document.body.classList.add(`theme-${theme}`);
    
    // Salvar preferência no localStorage
    localStorage.setItem('selectedTheme', theme);
    
    // Atualizar botões de tema
    updateThemeButtons(theme);
}

function updateThemeButtons(activeTheme) {
    document.querySelectorAll('.theme-btn').forEach(btn => {
        btn.classList.remove('active');
    });
    
    const buttons = document.querySelectorAll('.theme-btn');
    const themeMap = { 'classic': 0, 'light': 1, 'dark': 2 };
    if (themeMap[activeTheme] !== undefined) {
        buttons[themeMap[activeTheme]].classList.add('active');
    }
}

// Carregar tema salvo ou usar o padrão (Classic)
function loadTheme() {
    const savedTheme = localStorage.getItem('selectedTheme') || 'classic';
    setTheme(savedTheme);
}

// Carregar tema ao inicializar a página
loadTheme();

// Definir cor de fundo cinza escuro
document.body.style.backgroundColor = '#2c2c2c';
// carregar tarefas e usuários ao iniciar
loadTasks();
loadUsers();
