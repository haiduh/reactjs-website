import React from 'react'
import CardItem from './CardItem'
import '../css/Cards.css';

function Cards() {
  return (
    <div className='cards'>
        <h1> Explore These Learning Paths </h1>
      <div className="cards__container">
        <div className="cards__wrapper">
        <ul className="cards__items"> 
        <CardItem 
        src="images/ORFF360.jpg"
        text='Discover the fundamentals of Mathematics and build a strong foundation.'
        label='Mathematics'
        
        
        />
        <CardItem 
        src="images/dna-closely.jpg"
        text='Explore the world of Science and conduct exciting experiments.'
        label='Science'
        
        
        />


        </ul>
        </div>
      </div>
    </div>
  )
}

export default Cards
