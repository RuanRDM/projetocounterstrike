package projetocounterstrike.model;

/**
 *
 * @author ruan_
 */
public enum Modo {
    TERRORISTA, CONTRATERRORISTA;

        public static Modo getModo(String nameEnum){
        
        if(nameEnum.equals(Modo.TERRORISTA.toString()))
            
            return Modo.TERRORISTA;
        
        else if(nameEnum.equals(Modo.CONTRATERRORISTA.toString())){
            
            return Modo.CONTRATERRORISTA;
            
        }else{
            return null;
        }
    }
}
