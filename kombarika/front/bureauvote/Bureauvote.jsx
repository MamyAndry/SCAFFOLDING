import React, {useState, useEffect} from "react";
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

function Bureauvote(){
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
    const itemDetails = bureauvote.find(item => item.id === itemKey);
    setSelectedItem(itemDetails);
  };

	const [bureauvote, setBureauvote] = useState([]);
	
	const [idFokontany, setIdFokontany] = useState([]);
	
	

		const handleInputCodeBureauChange = (event) => {
			setSelectedItem({ ...selectedItem, codeBureau: event.target.value });
		};
		
		const handleInputCentreVoteChange = (event) => {
			setSelectedItem({ ...selectedItem, centreVote: event.target.value });
		};
		
		const handleSelectFokontanyChange = (event) => {
			setSelectedItem({ ...selectedItem, fokontany: event.target.value });
		};
		
		const handleInputIdChange = (event) => {
			setSelectedItem({ ...selectedItem, id: event.target.value });
		};
		
		const handleInputBureauVoteChange = (event) => {
			setSelectedItem({ ...selectedItem, bureauVote: event.target.value });
		};
		
		

	useEffect(() => {
		const getBureauvote = async () => {
			try {
				const response = await fetch(url + 'bureauvote' + '?start=' + currentPage + '&length=' + tableSize);
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const datas = await response.json();
				setCount(datas.count)
				setBureauvote(datas.data);
			} catch (error) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getBureauvote();
	}, [currentPage]);
	
	useEffect(() => {
		const getIdFokontany = async () => {
			try {
				const response = await fetch(url + 'idFokontany');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const datas = await response.json();
				setIdFokontany(datas);
			} catch (error) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getIdFokontany();
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
        const response = await fetch(url + 'bureauvote', {
          method: 'POST',
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
        const response = await fetch(url + 'bureauvote', {
          method: 'PUT',
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
      const response = await fetch(url + 'bureauvote', {
        method: 'DELETE',
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
      <li className="page-item"><a className="page-link" onClick={(e) => setCurrentPage(i * tableSize)}>{i + 1}</a></li>
    );
  }

  return (
    <>
      <div className="container">
          <div className="row justify-content-end">
              <div className="col" >   
                <div className="row">
                  <Button variant="primary" onClick={handleShowInsertModal}>
                      Add Bureauvote
                  </Button>
                </div>   
              </div>
              
          </div>
          { loading ? (
            <div className="row">
              <div className="d-flex justify-content-center">
                <div className="spinner-border" role="status">
                  <span className="visually-hidden">Loading...</span>
                </div>
              </div>
            </div>
          ):(       
          <>    
          <div className="row">
              <table className="table">
                  <thead id="table-head">
                      <tr>
							<th> Code Bureau </th>
							<th> Centre Vote </th>
							<th> Id Fokontany </th>
							<th> Id </th>
							<th> Bureau Vote </th>
				
                          <th></th>
                          <th></th>
                      </tr>
                  </thead>    
                  <tbody id="table-body">
                      {bureauvote.map((item) => (
                        <tr key={item.id}>
						<td>{item.codeBureau}</td>
						<td>{item.centreVote}</td>
						<td>{item.idFokontany.nomFokontany}</td>
						<td>{item.id}</td>
						<td>{item.bureauVote}</td>
				
                            <td>
                                <Button variant="danger" key={item.id} onClick={() => handleDeleteClick(item)}>
                                    Delete
                                </Button>
                            </td>   
                            <td>
                                <Button variant="warning" key={item.id} onClick={() => handleSelectItem(item.id)}>
                                    Update
                                </Button>
                            </td>
                        </tr>
                      ))}
                  </tbody>
              </table>
            </div>
            <div className="row">
                <nav aria-label="Page navigation example">
                <ul className="pagination justify-content-start">
                    {paginations}
                </ul>
                </nav>
            </div>
          </>
      )}
      </div>
    {/* SAVE */}
    <Modal show={showInsertModal} onHide={handleCloseInsertModal}>
        <Modal.Header closeButton>
        <Modal.Title>Add Bureauvote</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
					<div className="mb-3"> 
					 	<label className="form-label">Code Bureau</label> 
					 	<input className="form-control" type="text" name="codeBureau" />
					</div>
					<div className="mb-3"> 
					 	<label className="form-label">Centre Vote</label> 
					 	<input className="form-control" type="text" name="centreVote" />
					</div>
					<div className="mb-3"> 
					 	<label className="form-label">Id_fokontany</label> 
					 	<select className="form-control" name="idFokontany" id="select-idFokontany">
							{idFokontany.map((elt) => (
								<option value={elt.id}>{elt.nomFokontany}</option>
							))}
							
						</select>
					</div>
					<div className="mb-3"> 
					 	<label className="form-label">Bureau Vote</label> 
					 	<input className="form-control" type="text" name="bureauVote" />
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
            <Modal.Title>Update Bureauvote</Modal.Title>
        </Modal.Header>
        <Modal.Body>    
            <form action="" method="" id="update" onSubmit={handleUpdateSubmit}>
    					<div className="mb-3"> 
					 	<label className="form-label">Code Bureau</label> 
					 	<input className="form-control" type="text" name="codeBureau" onChange={handleInputCodeBureauChange} value={selectedItem ? selectedItem.codeBureau:''} />
					</div>
					<div className="mb-3"> 
					 	<label className="form-label">Centre Vote</label> 
					 	<input className="form-control" type="text" name="centreVote" onChange={handleInputCentreVoteChange} value={selectedItem ? selectedItem.centreVote:''} />
					</div>
					<div className="mb-3"> 
					 	<label className="form-label">IdFokontany</label> 
					 	<select className="form-control" name="idFokontany">
								{fokontany.map((elt) => (
								<option value={elt.id}>{elt.nomFokontany}</option>
							))}
							
					</select>
					</div>
					<div className="mb-3"> 
					 	<label className="form-label"></label> 
					 	<input className="form-control" type="hidden" name="id" onChange={handleInputIdChange} value={selectedItem ? selectedItem.id:''} />
					</div>
					<div className="mb-3"> 
					 	<label className="form-label">Bureau Vote</label> 
					 	<input className="form-control" type="text" name="bureauVote" onChange={handleInputBureauVoteChange} value={selectedItem ? selectedItem.bureauVote:''} />
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

export default Bureauvote;