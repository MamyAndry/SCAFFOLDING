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
                            Add Tags Publication
                        </Button>
                    </div>
                    <Modal id='insert' v-model:visible="insertForm">
                        <div id="body">
                            <form id="insert" @submit="e => insert(e)">
                                	<div className="mb-3"> 
	 	<label className="form-label">Publication</label> 
	 	<select className="form-control" name="publication" id="select-publication">
			<option v-for='elt in publication' :key='elt.idPublication' :value='elt.idPublication'>{{elt.description}}</option>
			
			
		</select>
	</div><div className="mb-3"> 
	 	<label className="form-label">Tags</label> 
	 	<select className="form-control" name="tags" id="select-tags">
			<option v-for='elt in tags' :key='elt.idPublication' :value='elt.idPublication'>{{elt.description}}</option>
			
			
		</select>
	</div>
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
                        			<th> Id Tag Publication </th>
			<th> Id Publication </th>
			<th> Tag </th>

                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in tagsPublication" :key="item.idTagPublication">
                        		<td>{{item.idTagPublication}}</td>
		<td>{{item.publication.description}}</td>
		<td>{{item.tags.description}}</td>

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
            <h2>Update Tags Publication</h2>
            <div id="body">
                <form id="update" @submit="e => update(e)" v-if="updateForm">
                    	<div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="idTagPublication" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idTagPublication</label> 
	 	<select className="form-control" name="publication">
			<option v-for='elt in publication' :key='elt.idPublication' :value='elt.idPublication'>{{elt.description}}</option>
	
	
	<option v-for='elt in tags' :key='elt.idPublication' :value='elt.idPublication'>{{elt.description}}</option>
	
	
	
	</select>
	</div><div className="mb-3"> 
	 	<label className="form-label">idTagPublication</label> 
	 	<select className="form-control" name="tags">
			<option v-for='elt in publication' :key='elt.idPublication' :value='elt.idPublication'>{{elt.description}}</option>
	
	
	<option v-for='elt in tags' :key='elt.idPublication' :value='elt.idPublication'>{{elt.description}}</option>
	
	
	
	</select>
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
            <h2>Delete Tags Publication</h2>
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
                	tags_publication: null,
	
	publication: null,
	
	tags: null,
	
	
            }
        },
        // eslint-disable-next-line vue/multi-word-component-names
        name: 'Tags Publication',
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
                    const response = await fetch(this.url + 'tagsPublication');
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
                    	fetch(this.url + 'tags_publication') 
	 .then(response => response.json()) 
	.then(data => { this.tags_publication = data; }) 
	 .catch(error => alert(error)); 
	fetch(this.url + 'publication') 
	 .then(response => response.json()) 
	.then(data => { this.publication = data; }) 
	 .catch(error => alert(error)); 
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
                    const response = await fetch(this.url + 'tagsPublication', {
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
                    const response = await fetch(this.url + 'tagsPublication', {
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
                    const response = await fetch(this.url + 'tagsPublication', {
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
