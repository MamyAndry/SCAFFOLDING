import React, {useState, useEffect} from "react";
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

function Commune(){
  const url = 'http://localhost:8080/';
  
  const [loading, setLoading] = useState(true);
  
  const [show, setShow] = useState(false);
  
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  
  const [show2, setShow2] = useState(false);
  
  const handleClose2 = () => setShow2(false);
  const handleShow2 = () => setShow2(true);
  
  const [error, setError] = useState(null);
  const [selectedItem, setSelectedItem] = useState(null);
  const handleSelectItem = (itemKey) => {
    handleShow2();
    const itemDetails = commune.find(item => item.id === itemKey);
    setSelectedItem(itemDetails);
  };

	const [commune, setCommune] = useState([]);
	
	const [district, setDistrict] = useState([]);
	
	

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
        const response = await fetch(url + 'commune', {
          method: 'POST',
          body: JSON.stringify(data),
          headers: {
            'Content-Type': 'application/json'
          }
        });
  
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
  
        handleClose();
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
        const response = await fetch(url + 'commune', {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(data)
        });
  
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        handleClose2();
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
      const response = await fetch(url + 'commune', {
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

	const handleInputNomCommuneChange = (event) => {
		setSelectedItem({ ...selectedItem, nomCommune: event.target.value });
	};
	
	const handleSelectDistrictChange = (event) => {
		setSelectedItem({ ...selectedItem, district: event.target.value });
	};
	
	const handleInputIdChange = (event) => {
		setSelectedItem({ ...selectedItem, id: event.target.value });
	};
	
	

	useEffect(() => {
		const getCommune = async () => {
			try {
				const response = await fetch(url + 'commune');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setCommune(data);
			} catch (error) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getCommune();
	}, []);
	useEffect(() => {
		const getDistrict = async () => {
			try {
				const response = await fetch(url + 'district');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setDistrict(data);
			} catch (error) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getDistrict();
	}, []);
	

  return (
    <>
      <div className="container">
          <div className="row justify-content-end">
              <div className="col" >   
                <div className="row">
                  <Button variant="primary" onClick={handleShow}>
                      Add Commune
                  </Button>
                </div>    

                  <Modal show={show} onHide={handleClose}>
                      <Modal.Header closeButton>
                      <Modal.Title>Add Commune</Modal.Title>
                      </Modal.Header>
                      <Modal.Body>
                          <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
	<div className="mb-3"> 
	 	<label className="form-label">Nom Commune</label> 
	 	<input className="form-control" type="text" name="nomCommune" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">id</label> 
	 	<select className="form-control" name="district" id="select-district">
			{region.map((elt) => (
				<option value={elt.id}>{elt.nomDistrict}</option>
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
              </div>
              
          </div>
          <div className="row">
              <table className="table">
                  <thead id="table-head">
                      <tr>
			<th> Nom Commune </th>
			<th> Id District </th>
			<th> Id </th>

                          <th></th>
                          <th></th>
                      </tr>
                  </thead>    
                  <tbody id="table-body">
                      {commune.map((item) => (
                              <tr key={item.id}>
		<td>{item.nomCommune}</td>
		<td>{item.district.nomDistrict}</td>
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
              <Modal show={show2} onHide={handleClose2}>
                  <Modal.Header closeButton>
                      <Modal.Title>Update Commune</Modal.Title>
                  </Modal.Header>
                  <Modal.Body>    
                      <form action="" method="" id="update" onSubmit={handleUpdateSubmit}>
	<div className="mb-3"> 
	 	<label className="form-label">Nom Commune</label> 
	 	<input className="form-control" type="text" name="nomCommune" onChange={handleInputNomCommuneChange} value={selectedItem ? selectedItem.nomCommune:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">id</label> 
	 	<select className="form-control" name="district">
			{region.map((elt) => (
		<option value={elt.id}>{elt.nomDistrict}</option>
	))}
	
	
	</select>
	</div><div className="mb-3"> 
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
          </div>
      </div>
    </>
  )
}

export default Commune;
