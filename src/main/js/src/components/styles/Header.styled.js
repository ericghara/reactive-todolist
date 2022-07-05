import styled from 'styled-components'

export const StyledHeader = styled.header`
  background-color: #ffffff;
  padding: 5px 0;
  font-weight: 700;
  font-size: 16px;
  color: #4c3f3f;
`

export const Nav = styled.nav`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 5px 15px 5px 15px;
 `
export const Button = styled.button`
    border-radius: 50px;
    border: none;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
    cursor: pointer;
    font-size: 16px;
    font-weight: 700;
    padding: 15px 60px;
    background-color: #fff3c7;
    color: #545454;

    &:hover {
        opacity: 0.9;
        transform: scale(0.98);
      }
`