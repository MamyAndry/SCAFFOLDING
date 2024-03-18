<template>
    <header>
        <title>Vue 3</title>
        <link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap/dist/css/bootstrap.min.css" />
        <link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.min.css" />
    </header>
    <div class="container">
        <div v-if="loading">
            Loading ...
        </div>
        <!-- Insert start -->
        <div class="row">
            <div class="row justify-content-end">
                <div class="col">
                    <div class="row">
                        <Button variant="primary" class="btn btn-primary" @click="insertForm = !insertForm">
                            Add Tags
                        </Button>
                    </div>
                    <Modal id='insert' v-model:visible="insertForm">
                        <div id="body">
                            <form id="insert" @submit="e => insert(e)">
                                	
                                <div className="mb-3">
                                    <Button variant="primary" type= "submit" >
                                        Insert
                                    </Button>
                                </div>
                            </form>
                        </div>
                    </Modal>
                </div>
            </div>
        </div>
        <!-- Insert end -->
        <!-- List start -->
        <div class="row">
            <table class="table">
                <thead>
                    <tr>
                        			<th> Tag </th>

                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in tags" :key="item.tag">
                        		<td>{{item.tag}}</td>

                        <td>
                            <Button variant="primary" class="btn btn-warning" @click="targetItem = item; updateForm = !updateForm">
                                Edit
                            </Button>
                        </td>
                        <td>
                            <Button variant="danger" class="btn btn-danger" @click="targetItem = item; deleteForm = !deleteForm">
                                Delete
                            </Button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- List end -->
        <!-- Update start -->
        <Modal id="update" v-model:visible="updateForm">
            <h2>Update Tags</h2>
            <div id="body">
                <form id="update" @submit="e => update(e)" v-if="updateForm">
                    	<div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="tag" />
	</div>
	
                    <div className="mb-3">
                        <Button class="btn btn-primary" type= "submit" >
                            Update
                        </Button>
                      </div>
                </form>
            </div>
        </Modal>
        <!-- Update end -->
        <!-- Delete start -->
        <Modal id="delete" v-model:visible="deleteForm">
            <h2>Delete Tags</h2>
            <div id="body">
                <form id="delete" @submit="del()" v-if="deleteForm">
                    <div className="mb-3">
                        <Button class="btn btn-danger" type= "submit" >
                            Delete
                        </Button>
                      </div>
                </form>
            </div>
        </Modal>
        <!-- Delete end -->
    </div>
</template>

<script>
    import { Modal } from 'usemodal-vue3';
    
    export default{
        data(){
            return {
                url: 'http://localhost:8080',
                items: null,
                targetItem: null,
                insertForm: false,
                deleteForm: false,
                updateForm: false,
                loading: false,
                	tags: null,
	
	
            }
        },
        // eslint-disable-next-line vue/multi-word-component-names
        name: 'Tags',
        components: {
            Modal
        },
        mounted(){
            this.getAll();
        },
        methods: {
            async getAll() {
                /*try {
                    this.loading = true;
                    const response = await fetch(this.url + 'tags');
                    if (!response.ok) {
                        throw new Error('Erreur réseau');
                    }
                    const data = await response.json();
                    this.items = data;
                    this.loading = false;
                } catch (error) {
                    if(this.loading) this.loading = false;
                    alert(error);
                }*/
                try{
                    	fetch(this.url + 'tags') 
	 .then(response => response.json()) 
	.then(data => { this.tags = data; }) 
	 .catch(error => alert(error)); 
	
                }catch (error) {
                    if(this.loading) this.loading = false;
                    alert(error);
                }   
            },
            async update(event) {
                event.preventDefault();
                const updateData = this.fetchFormData(event.target);
                try {
                    const response = await fetch(this.url + 'tags', {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(updateData)
                    });
                    if (!response.ok) {
                        throw new Error('Erreur réseau');
                    }
                    this.updateForm = false;
                    this.getAll(); // reload local data
                } catch (error) {
                    alert(error);
                }
            },
            async del() {
                try {
                    const response = await fetch(this.url + 'tags', {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(this.targetItem)
                    });
                    if (!response.ok) {
                        throw new Error('Erreur réseau');
                    }
                    this.deleteForm = false;
                    this.getAll(); // reload local data
                } catch (error) {
                    alert(error);
                }
            },
            async insert(event) {
                event.preventDefault();
                const insertData = this.fetchFormData(event.target);
                try {
                    const response = await fetch(this.url + 'tags', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(insertData)
                    });
                    if (!response.ok) {
                        throw new Error('Erreur réseau');
                    }
                    console.log("Réponse", response);
                    this.insertForm = false;
                    this.getAll(); // reload local data
                } catch (error) {
                    alert(error);
                }
            },
            fetchFormData(dataForm) {
                var data = {};
                for (var i = 0; i < dataForm.length; i++) {
                    if (dataForm[i].type !== "submit") {
                        data[dataForm[i].name] = dataForm[i].value;
                    }
                }
                return data;
            }
        }

    }
</script>
