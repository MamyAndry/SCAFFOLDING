import React, {useState, useEffect} from "react";
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

function Fokontany(){
  const url = 'http://localhost:8080/demo_war_exploded/';


  const [loading, setLoading] = useState(true);

  const [count, setCount] = useState(1);
  const [currentPage, setCurrentPage] = useState(0);
  const tableSize = 5;

  
  const [showInsertModal, setShowInsertModal] = useState(false);
  
  const handleCloseInsertModal = () => setShowInsertModal(false);
  const handleShowInsertModal = () => setShowInsertModal(true);
  
  const [showUpdateModal, setShowUpdateModal] = useState(false);
  
  const handleCloseUpdateModal = () => setShowUpdateModal(false);
  const handleShowUpdateModal = () => setShowUpdateModal(true);
  
  const [error, setError] = useState(null);
  const [selectedItem, setSelectedItem] = useState(null);
  const handleSelectItem = (itemKey) => {
    handleShowUpdateModal();
    const itemDetails = fokontany.find(item => item.id === itemKey);
    setSelectedItem(itemDetails);
  };

	const [fokontany, setFokontany] = useState([]);
	
	const [idCommune, setIdCommune] = useState([]);
	
	

		const handleInputNomFokontanyChange = (event) => {
			setSelectedItem({ ...selectedItem, nomFokontany: event.target.value });
		};
		
		const handleInputIdChange = (event) => {
			setSelectedItem({ ...selectedItem, id: event.target.value });
		};
		
		const handleSelectCommuneChange = (event) => {
			setSelectedItem({ ...selectedItem, commune: event.target.value });
		};
		
		

	useEffect(() => {
		const getFokontany = async () => {
			try {
				const response = await fetch(url + 'fokontany/pagination' + '?start=' + currentPage + '&length=' + tableSize, {credentials: 'include'});
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const datas = await response.json();
				setCount(datas.count)
				setFokontany(datas.data);
			} catch (error) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getFokontany();
	}, [currentPage]);
	
	useEffect(() => {
		const getIdCommune = async () => {
			try {
				const response = await fetch(url + 'commune', {credentials: 'include'});
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const datas = await response.json();
				setIdCommune(datas);
			} catch (error) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getIdCommune();
	}, []);
	
	

//////// SAVE
  const handleSaveSubmit = async (event) => {
      event.preventDefault();
      const form = event.target;
      const formData = new FormData(form);
      const data = {};
  
      for (let [key, value] of formData.entries()) {
        if (form.elements[key].tagName === 'SELECT') {
          data[key] = { id: value };
        } else {
          data[key] = value;
        }
      }
  
      try {
        const response = await fetch(url + 'fokontany', {
          method: 'POST',
          credentials: 'include',
          body: JSON.stringify(data),
          headers: {
            'Content-Type': 'application/json'
          }
        });
  
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
  
        handleCloseInsertModal();
        // If you want to reload the page after success
        window.location.reload();
      } catch (error) {
        console.log('Error:', error);
      }
  };

//////// UPDATE 
  const handleUpdateSubmit = async (event) => {
      event.preventDefault();
      const form = event.target;
      const formData = new FormData(form);
      const data = {};
      for (let [key, value] of formData.entries()) {
        if (form.elements[key].tagName === 'SELECT') {
          data[key] = { id: value };
        } else {
          data[key] = value;
        }
      }
      try {
        const response = await fetch(url + 'fokontany', {
          method: 'PUT',
          credentials: 'include',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(data)
        });
  
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        handleCloseUpdateModal();
        // If you want to reload the page after success
        window.location.reload();
      } catch (error) {
        console.error('Error:', error);
      }
  };

//////// DELETE
  const handleDeleteClick = async (item) => {
    try {
      console.log(item);
      const response = await fetch(url + 'fokontany', {
        method: 'DELETE',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(item)
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      // If you want to reload the page after success
      window.location.reload();
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const paginations = [];
  const pageNumber = count / tableSize;
  for(let i = 0; i < pageNumber; i++){
    paginations.push(
      <li className="page-item"><a className="page-link" style={{'color':'#007F73'}} onClick={(e) => setCurrentPage(i * tableSize)}>{i + 1}</a></li>
    );
  }

  return (
    <>
        <div className="container">
      <div class="row detail-publi"
      style={{ 'padding': '3%', 'padding-left': '2.5%' }}>
      <div className="col-12 col-lg-12">
        <div className="single_product_thumb">
        { loading ? (
            <div className="row">
              <div className="d-flex justify-content-center">
                <div className="spinner-border" role="status">
                  <span className="visually-hidden">Loading...</span>
                </div>
              </div>
            </div>
          ):(  
          <table className="table">
            <thead>
              <tr>
							<th> Nom Fokontany </th>
							<th> Id </th>
							<th> Id Commune </th>
				

              <th></th>
              </tr>
            </thead>
            <tbody>
              
            {fokontany.map((item) => (
                        <tr key={item.id}>
            						<td>{item.nomFokontany}</td>
						<td>{item.id}</td>
						<td>{item.idCommune.nomCommune}</td>
				
				
            <td style={{'text-align': 'right'}}>
              <button key={item.id} onClick={() => handleSelectItem(item.id)}  style={{'margin-right': '20px', 'background-color': '#007F73', 'color': 'white'}} className="btn" >
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                </svg>
                
              </button>
              <button className="btn btn-danger" key={item.id} onClick={() => handleDeleteClick(item)}>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                  <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"/>
                </svg>
              </button>
            </td>

            </tr>
          ))}
          </tbody>
        </table>
          )}
      </div>
    </div>
    <div className="row" style={{'overflow-x':'scroll', 'cursor':'pointer'}}>
                <nav aria-label="Page navigation example">
                <ul className="pagination justify-content-start">
                    {paginations}
                </ul>
                </nav>
    </div>
    </div>
    </div>

      <div className="container">
          <div className="row justify-content-end">
              <div className="col" >   
                <div className="row">
                  <Button variant="primary" style={{'background-color':'#007F73'}} onClick={handleShowInsertModal}>
                      Add Fokontany
                  </Button>
                </div>   
              </div>
              
          </div>
        </div>
      
    {/* SAVE */}
    <Modal show={showInsertModal} onHide={handleCloseInsertModal}>
        <Modal.Header closeButton>
        <Modal.Title>Add Fokontany</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
					<div className="mb-3"> 
					 	<label className="form-label">Nom Fokontany</label> 
					 	<input className="form-control" type="text" name="nomFokontany" />
					</div>
					<div className="mb-3"> 
					 	<label className="form-label">Id Commune</label> 
					 	<select className="form-control" name="idCommune">
								{idCommune.map((elt) => (
								<option value={elt.id}>{elt.nomCommune}</option>
							))}
							
					</select>
					</div>
					
                <div className="mb-3">
                <Button variant="primary" type= "submit" >
                    Save Changes
                </Button>
                </div>
            </form>
        </Modal.Body>
        <Modal.Footer>
        </Modal.Footer>
    </Modal>


    {/* UPDATE */}
    <Modal show={showUpdateModal} onHide={handleCloseUpdateModal}>
        <Modal.Header closeButton>
            <Modal.Title>Update Fokontany</Modal.Title>
        </Modal.Header>
        <Modal.Body>    
            <form action="" method="" id="update" onSubmit={handleUpdateSubmit}>
    					<div className="mb-3"> 
					 	<label className="form-label">Nom Fokontany</label> 
					 	<input className="form-control" type="#type#" name="nomFokontany" onChange={handleInputNomFokontanyChange} value={selectedItem ? selectedItem.nomFokontany:''} />
					</div>
					<div className="mb-3"> 
					 	<label className="form-label"></label> 
					 	<input className="form-control" type="hidden" name="id" onChange={handleInputIdChange} value={selectedItem ? selectedItem.id:''} />
					</div>
					<div className="mb-3"> 
					 	<label className="form-label">Id Commune</label> 
					 	<select className="form-control" name="idCommune">
								{idCommune.map((elt) => (
								<option value={elt.id}>{elt.nomCommune}</option>
							))}
							
					</select>
					</div>
					
            <div className="mb-3">
            <Button variant="warning" type= "submit" >
                Save Changes
            </Button>
            </div>
            </form>  
        </Modal.Body>
        <Modal.Footer>

        </Modal.Footer>
    </Modal>
    </>
  )
}

export default Fokontany;
