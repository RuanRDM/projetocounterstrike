package projetocounterstrike.model;

/**
 *
 * @author ruan_
 */
public enum Tipo {
    MANUAL, AUTOMATICA, BRANCA, FOGO;

        public static Tipo getTipo(String nameEnum){
        
        if(nameEnum.equals(Tipo.MANUAL.toString()))
            
            return Tipo.MANUAL;
        
        else if(nameEnum.equals(Tipo.AUTOMATICA.toString())){
            
            return Tipo.AUTOMATICA;
            
        }else if(nameEnum.equals(Tipo.BRANCA.toString())){
            
            return Tipo.BRANCA;
            
        }else if(nameEnum.equals(Tipo.FOGO.toString())){
            
            return Tipo.FOGO;
            
        }else{
            return null;
        }
    }
}
