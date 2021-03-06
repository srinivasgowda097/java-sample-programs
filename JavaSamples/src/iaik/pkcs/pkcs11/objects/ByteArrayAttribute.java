/* Copyright  (c) 2002 Graz University of Technology. All rights reserved.
 *
 * Redistribution and use in  source and binary forms, with or without 
 * modification, are permitted  provided that the following conditions are met:
 *
 * 1. Redistributions of  source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in  binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *  
 * 3. The end-user documentation included with the redistribution, if any, must
 *    include the following acknowledgment:
 * 
 *    "This product includes software developed by IAIK of Graz University of
 *     Technology."
 * 
 *    Alternately, this acknowledgment may appear in the software itself, if 
 *    and wherever such third-party acknowledgments normally appear.
 *  
 * 4. The names "Graz University of Technology" and "IAIK of Graz University of
 *    Technology" must not be used to endorse or promote products derived from 
 *    this software without prior written permission.
 *  
 * 5. Products derived from this software may not be called 
 *    "IAIK PKCS Wrapper", nor may "IAIK" appear in their name, without prior 
 *    written permission of Graz University of Technology.
 *  
 *  THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE LICENSOR BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 *  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY  OF SUCH DAMAGE.
 */

package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.wrapper.Functions;



/**
 * Objects of this class represent a byte-array attribute of a PKCS#11 object
 * as specified by PKCS#11.
 *
 * @author <a href="mailto:Karl.Scheibelhofer@iaik.at"> Karl Scheibelhofer </a>
 * @version 1.0
 * @invariants
 */
public class ByteArrayAttribute extends Attribute {

  /**
   * Default constructor - only for internal use in AttributeArrayAttribute.getValueString().
   */
	ByteArrayAttribute() {
  	super();
  }
	
  /**
   * Constructor taking the PKCS#11 type of the attribute.
   *
   * @param type The PKCS'11 type of this attribute; e.g.
   *             PKCS11Constants.CKA_VALUE.
   * @preconditions (type <> null)
   * @postconditions
   */
  public ByteArrayAttribute(Long type) {
    super(type);
  }

  /**
   * Set the byte-array value of this attribute. Null, is also valid.
   * A call to this method sets the present flag to true.
   *
   * @param value The byte-array value to set. May be null.
   * @preconditions
   * @postconditions
   */
  public void setByteArrayValue(byte[] value) {
    ckAttribute_.pValue = value;
    present_ = true;
  }

  /**
   * Get the byte-array value of this attribute. Null, is also possible.
   *
   * @return The byte-array value of this attribute or null.
   * @preconditions
   * @postconditions
   */
  public byte[] getByteArrayValue() {
    return (byte[]) ckAttribute_.pValue ;
  }

  /**
   * Get a string representation of the value of this attribute.
   *
   * @return A string representation of the value of this attribute.
   * @preconditions
   * @postconditions (result <> null)
   */
  protected String getValueString() {
    String valueString;

    if ((ckAttribute_ != null) && (ckAttribute_.pValue != null)) {
      valueString = Functions.toHexString((byte[]) ckAttribute_.pValue);
    } else {
      valueString = "<NULL_PTR>";
    }

    return valueString ;
  }

  /**
   * Compares all member variables of this object with the other object.
   * Returns only true, if all are equal in both objects.
   *
   * @param otherObject The other object to compare to.
   * @return True, if other is an instance of this class and all member
   *         variables of both objects are equal. False, otherwise.
   * @preconditions
   * @postconditions
   */
  public boolean equals(java.lang.Object otherObject) {
    boolean equal = false;

    if (otherObject instanceof ByteArrayAttribute) {
      ByteArrayAttribute other = (ByteArrayAttribute) otherObject;
      equal = (this == other)
              || (((this.present_ == false) && (other.present_ == false))
                  || (((this.present_ == true) && (other.present_ == true))
                      && ((this.sensitive_ == other.sensitive_)
                          && Functions.equals((byte[]) this.ckAttribute_.pValue, (byte[]) other.ckAttribute_.pValue))));
    }

    return equal ;
  }

  /**
   * The overriding of this method should ensure that the objects of this class
   * work correctly in a hashtable.
   *
   * @return The hash code of this object.
   * @preconditions
   * @postconditions
   */
  public int hashCode() {
    return (ckAttribute_.pValue != null) ? Functions.hashCode((byte[]) ckAttribute_.pValue) : 0 ;
  }

}