package ca.etsmtl.log720.lab1;

import javax.swing.*;
import javax.swing.text.*;

/**
 * This is a component that provides a textfield that accepts only
 * integer values. You can set the minimum and maximum value in the
 * constructor and in the get/set methods.
 *@author Andreas Schmitz
 *@version 7 Dec 2000
 */

public class JIntField extends JTextField{
	private static final long serialVersionUID = 1L;
	private transient int min,max;

    /**
     * Constructor whom you must provide the initial value, min and
     * max value.
     *@param val the initial value
     *@param minVal the minimum value
     *@param maxVal the maximum value
     */
    public JIntField(final int val, final int minVal, final int maxVal ){
    	super();
		min=minVal;
		max=maxVal;
		setValue(val);
    }

    /**
     * Constructor that intitalizes the JIntField with min=0, max=100
     * and actual value=0. You could use this for percentual
     * JIntFields together with a scrollbar to set the value. If you
     * think, I should start with 1 instead of 0, <a
     * href="mailto:stranger@sammael.tabu.stw-bonn.de">mail me</a>.
     */
    public JIntField(){
    	super();
		min=0;
		max=100;
		setValue(0);
    }

    /**
     * Overrides something in JTextField; it just returns an
     * IntDocument (This <i>IS</i> a JIntField...)
     *@return returns the IntDocument
     */
    protected Document createDefaultModel(){
	return new IntDocument();
    }

    /**
     * Returns the actual value.
     *@return the value
     */
    public int getValue(){
	try{
	    return Integer.parseInt(getText());
	}catch(NumberFormatException e){
	    return min;
	}
    }

    /**
     * Sets the actual value.
     *@param val the value
     */
    public void setValue(final int val ){
    	setText(""+val);
    }

    /**
     * Sets the maximum value.
     *@param val the value
     */
    public void setMaxValue(final int val ){
	max=val;
    }

    /**
     * Sets the minimum value.
     *@param val the value
     */
    public void setMinValue( int val ){
	min=val;
    }

    /**
     * Returns the maximum value.
     *@return the value
     */
    public int getMaxValue(){
	return max;
    }

    /**
     * Returns the minimum value.
     *@return the value
     */
    public int getMinValue(){
	return min;
    }

    private class IntDocument extends PlainDocument{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void insertString( int offs, String s, AttributeSet a )
		throws BadLocationException{
		    char[] src=s.toCharArray();
		    char[] res=new char[src.length];
		    int j=0;
		    for(int i=0;i<src.length;i++){
			if(Character.isDigit(src[i])) res[j++]=src[i];
		    }
		    super.insertString( offs, new String( res, 0, j), a);
		    if(getValue()>max) setValue(max);
		    if(getValue()<min) setValue(min);
		}
    }
}