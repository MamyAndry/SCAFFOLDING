import React, {useState, useEffect} from "react";
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

function Region(){
  const url = 'http://localhost:8080/testkarana/';


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
    const itemDetails = region.find(item => item.id === itemKey);
    setSelectedItem(itemDetails);
  };

	const [region, setRegion] = useState([]);
	
	

		const handleInputNomRegionChange = (event) => {
			setSelectedItem({ ...selectedItem, nomRegion: event.target.value });
		};
		
		const handleInputIdChange = (event) => {
			setSelectedItem({ ...selectedItem, id: event.target.value });
		};
		
		

	useEffect(() => {
		const getRegion = async () => {
			try {
				const response = await fetch(url + 'region/pagination' + '?start=' + currentPage + '&length=' + tableSize);
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const datas = await response.json();
				setCount(datas.count)
				setRegion(datas.data);
			} catch (error) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getRegion();
	}, [currentPage]);
	
	

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
        const response = await fetch(url + 'region', {
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
        const response = await fetch(url + 'region', {
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
      const response = await fetch(url + 'region', {
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
                      Add Region
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
							<th> Nom Region </th>
							<th> Id </th>
				
                          <th></th>
                          <th></th>
                      </tr>
                  </thead>    
                  <tbody id="table-body">
                      {region.map((item) => (
                        <tr key={item.id}>
						<td>{item.nomRegion}</td>
						<td>{item.id}</td>
				
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
        <Modal.Title>Add Region</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
					<div className="mb-3"> 
					 	<label className="form-label">Nom Region</label> 
					 	<input className="form-control" type="text" name="nomRegion" />
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
            <Modal.Title>Update Region</Modal.Title>
        </Modal.Header>
        <Modal.Body>    
            <form action="" method="" id="update" onSubmit={handleUpdateSubmit}>
    					<div className="mb-3"> front/bureauvote front/district front/commune front/fokontany front/region
					 	<label className="form-label">Nom Region</label> 
					 	<input className="form-control" type="#type#" name="nomRegion" onChange={handleInputNomRegionChange} value={selectedItem ? selectedItem.nomRegion:''} />
					</div>
					<div className="mb-3"> front/bureauvote front/district front/commune front/fokontany front/region
					 	<label className="form-label"></label> 
					 	<input className="form-control" type="hidden" name="id" onChange={handleInputIdChange} value={selectedItem ? selectedItem.id:''} />
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

export default Region;
