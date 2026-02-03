const API_URL = "http://localhost:8080/api/tasks";

// Ordem de prioridade para ordenação
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

        // Ordenar tarefas por prioridade (High > Medium > Low)
        const sortedTasks = tasks.sort((a, b) => {
            return PRIORITY_ORDER[a.priority] - PRIORITY_ORDER[b.priority];
        });

        const list = document.getElementById("taskList");
        const countElement = document.getElementById("taskCount");
        
        // Atualizar contador
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
            `;
            
            list.appendChild(taskElement);
        });
    } catch (error) {
        console.error('Error loading tasks:', error);
        showNotification('Error loading tasks', 'error');
    }
}

async function createTask() {
    const title = document.getElementById("title").value.trim();
    const description = document.getElementById("description").value.trim();
    const priority = document.getElementById("priority").value;
    const userId = document.getElementById("userId").value;

    // Validação
    if (!title) {
        showNotification('Please enter a title', 'error');
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

        // Limpar formulário
        document.getElementById("title").value = "";
        document.getElementById("description").value = "";
        document.getElementById("priority").value = "MEDIUM";
        document.getElementById("userId").value = "";

        showNotification('Task created successfully!', 'success');
        loadTasks();
    } catch (error) {
        console.error('Error creating task:', error);
        showNotification('Error creating task', 'error');
    }
}

// Função para escapar HTML e prevenir XSS
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

// Função para mostrar notificações
function showNotification(message, type = 'info') {
    // Implementação simples com alert - pode ser melhorada com toast notifications
    if (type === 'error') {
        alert('❌ ' + message);
    } else if (type === 'success') {
        alert('✅ ' + message);
    } else {
        alert(message);
    }
}

// Carregar tarefas ao iniciar
loadTasks();
